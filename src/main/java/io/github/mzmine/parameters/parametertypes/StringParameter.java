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

import java.util.Collection;
import java.util.Optional;

import org.controlsfx.property.editor.PropertyEditor;
import org.w3c.dom.Element;

import io.github.mzmine.parameters.Parameter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class StringParameter implements Parameter<String> {

    private final String name, description, category;
    private final EventHandler<ActionEvent> autoSetAction;
    private String value;

    public StringParameter(String name, String description) {
        this(name, description, null, null, null);
    }

    public StringParameter(String name, String description, String category,
            String defaultValue) {
        this(name, description, category, defaultValue, null);
    }

    public StringParameter(String name, String description, String category,
            String defaultValue, EventHandler<ActionEvent> autoSetAction) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.value = defaultValue;
        this.autoSetAction = autoSetAction;
    }

    /**
     * @see net.sf.mzmine.data.Parameter#getName()
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @see net.sf.mzmine.data.Parameter#getDescription()
     */
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public Class<?> getType() {
        return String.class;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        this.value = (String) value;
    }

    @Override
    public StringParameter clone() {
        StringParameter copy = new StringParameter(name, description, category,
                value, autoSetAction);
        return copy;
    }

    @Override
    public void loadValueFromXML(Element xmlElement) {
        value = xmlElement.getTextContent();
    }

    @Override
    public void saveValueToXML(Element xmlElement) {
        if (value == null)
            return;
        xmlElement.setTextContent(value);
    }

    @Override
    public boolean checkValue(Collection<String> errorMessages) {
        if ((value == null) || (value.trim().length() == 0)) {
            errorMessages.add(name + " is not set properly");
            return false;
        }
        return true;
    }

    @Override
    public Optional<Class<? extends PropertyEditor<?>>> getPropertyEditorClass() {
        return Optional.of(StringEditor.class);
    }

    public EventHandler<ActionEvent> getAutoSetAction() {
        return autoSetAction;
    }

}
