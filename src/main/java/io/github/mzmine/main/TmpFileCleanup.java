/*
 * Copyright 2006-2016 The MZmine 3 Development Team
 * 
 * This file is part of MZmine 3.
 * 
 * MZmine 3 is free software; you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * MZmine 3 is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * MZmine 3; if not, write to the Free Software Foundation, Inc., 51 Franklin St,
 * Fifth Floor, Boston, MA 02110-1301 USA
 */

package io.github.mzmine.main;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TmpFileCleanup implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run() {

        logger.debug("Checking for old temporary files...");
        try {

            // Find all temporary files with the mask mzmine*.scans
            File tempDir = new File(System.getProperty("java.io.tmpdir"));
            File remainingTmpFiles[] = tempDir.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.matches("mzmine.*\\.scans");
                }
            });

            if (remainingTmpFiles != null)
                for (File remainingTmpFile : remainingTmpFiles) {

                    // Skip files created by someone else
                    if (!remainingTmpFile.canWrite())
                        continue;

                    // Try to obtain a lock on the file
                    RandomAccessFile rac = new RandomAccessFile(
                            remainingTmpFile, "rw");

                    FileLock lock = rac.getChannel().tryLock();
                    rac.close();

                    if (lock != null) {
                        // We locked the file, which means nobody is using it
                        // anymore and it can be removed
                        logger.debug("Removing unused temporary file "
                                + remainingTmpFile);
                        remainingTmpFile.delete();
                    }

                }
        } catch (IOException e) {
            logger.warn("Error while checking for old temporary files", e);
        }

    }
}
