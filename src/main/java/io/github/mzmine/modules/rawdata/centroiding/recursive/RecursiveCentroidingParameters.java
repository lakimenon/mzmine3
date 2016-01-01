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

package io.github.mzmine.modules.rawdata.centroiding.recursive;

import io.github.mzmine.parameters.Parameter;
import io.github.mzmine.parameters.ParameterSet;
import io.github.mzmine.parameters.parametertypes.StringParameter;
import io.github.mzmine.parameters.parametertypes.ranges.DoubleRangeParameter;
import io.github.mzmine.parameters.parametertypes.selectors.RawDataFilesParameter;

public class RecursiveCentroidingParameters extends ParameterSet {

    public static final RawDataFilesParameter dataFiles = new RawDataFilesParameter();

    public static final DoubleRangeParameter mzPeakWidth = new DoubleRangeParameter(
            "m/z peak width",
            "Minimum and maximum acceptable peak width in m/z", "");

    public static final StringParameter suffix = new StringParameter("Suffix",
            "Suffix to add to the raw data file name", "centroided");

    public RecursiveCentroidingParameters() {
        super(new Parameter[] { dataFiles, mzPeakWidth, suffix });
    }

}
