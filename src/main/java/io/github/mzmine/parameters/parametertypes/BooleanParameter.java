/*
 * Copyright 2006-2015 The MZmine 3 Development Team
 * 
 * This file is part of MZmine 2.
 * 
 * MZmine 2 is free software; you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * MZmine 2 is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * MZmine 2; if not, write to the Free Software Foundation, Inc., 51 Franklin
 * St, Fifth Floor, Boston, MA 02110-1301 USA
 */

package io.github.mzmine.parameters.parametertypes;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.w3c.dom.Element;

import io.github.mzmine.parameters.Parameter;

public class BooleanParameter implements Parameter<Boolean> {

    private final @Nonnull String name, description;
    private Boolean value;

    public BooleanParameter(@Nonnull String name, @Nonnull String description) {
        this(name, description, null);
    }

    public BooleanParameter(@Nonnull String name, @Nonnull String description,
            Boolean defaultValue) {
        this.name = name;
        this.description = description;
        this.value = defaultValue;
    }

    /**
     * @see net.sf.mzmine.data.Parameter#getName()
     */
    @Override
    public @Nonnull String getName() {
        return name;
    }

    /**
     * @see net.sf.mzmine.data.Parameter#getDescription()
     */
    @Override
    public @Nonnull String getDescription() {
        return description;
    }

    @Override
    public Class<?> getType() {
        return Boolean.class;
    }

    public Boolean getValue() {
        return value;
    }

    @Override
    public void setValue(@Nullable Object value) {
        this.value = (Boolean) value;
    }

    @Override
    public @Nonnull BooleanParameter clone() {
        BooleanParameter copy = new BooleanParameter(name, description, value);
        return copy;
    }

    @Override
    public void loadValueFromXML(@Nonnull Element xmlElement) {
        String rangeString = xmlElement.getTextContent();
        if (rangeString.length() == 0)
            return;
        this.value = Boolean.valueOf(rangeString);
    }

    @Override
    public void saveValueToXML(@Nonnull Element xmlElement) {
        if (value == null)
            return;
        xmlElement.setTextContent(value.toString());
    }

}