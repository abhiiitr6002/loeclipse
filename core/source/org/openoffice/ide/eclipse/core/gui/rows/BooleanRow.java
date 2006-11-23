/*************************************************************************
 *
 * $RCSfile: BooleanRow.java,v $
 *
 * $Revision: 1.4 $
 *
 * last change: $Author: cedricbosdo $ $Date: 2006/11/23 18:27:18 $
 *
 * The Contents of this file are made available subject to the terms of
 * either of the GNU Lesser General Public License Version 2.1
 *
 * Sun Microsystems Inc., October, 2000
 *
 *
 * GNU Lesser General Public License Version 2.1
 * =============================================
 * Copyright 2000 by Sun Microsystems, Inc.
 * 901 San Antonio Road, Palo Alto, CA 94303, USA
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1, as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston,
 * MA 02111-1307 USA
 * 
 * The Initial Developer of the Original Code is: Sun Microsystems, Inc..
 *
 * Copyright: 2002 by Sun Microsystems, Inc.
 *
 * All Rights Reserved.
 *
 * Contributor(s): Cedric Bosdonnat
 *
 *
 ************************************************************************/
package org.openoffice.ide.eclipse.core.gui.rows;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * Usefull class to create a boolean choice row with a label. For further 
 * informations on rows, please report to {@link LabeledRow}.
 * 
 * @author cbosdonnat
 *
 */
public class BooleanRow extends LabeledRow {
	
	private boolean mValue;
	
	/**
	 * Creates a new boolean raw. The parent composite should have a grid layout
	 * with 2 or 3 horizontal spans.
	 * 
	 * @param parent the parent composite where to create the row.
	 * @param property the property name of the row.
	 * @param label the label to print on the left of the raw
	 */
	public BooleanRow(Composite parent, String property, String label) {
		super(property);
		
		Button checkbox = new Button(parent, SWT.CHECK);
		checkbox.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				toggleValue();
			};
		});
		checkbox.setText(label);
		
		Label text = new Label(parent, SWT.NONE);
		
		createContent(parent, checkbox,text, null);
	}

	/*
	 *  (non-Javadoc)
	 * @see org.openoffice.ide.eclipse.core.gui.rows.LabeledRow#setLabel(java.lang.String)
	 */
	public void setLabel(String newLabel){
		((Label)mField).setText(newLabel);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.openoffice.ide.eclipse.core.gui.rows.LabeledRow#setTooltip(java.lang.String)
	 */
	public void setTooltip(String tooltip) {
		((Button)mLabel).setToolTipText(tooltip);
	}
	
	/**
	 * Set a new value to the raw
	 * 
	 * @param aValue the new value
	 */
	public void setValue(boolean aValue){
		if (mValue != aValue){
			((Button)mLabel).setSelection(aValue);
			toggleValue();
		}
	}
	
	/**
	 * Changes the value of the raw
	 */
	public void toggleValue(){
		mValue = !mValue;
		fireFieldChangedEvent(new FieldEvent(mProperty, getValue()));
	}
	
	/**
	 * Returns the value of the raw as a boolean
	 */
	public boolean getBooleanValue(){
		return mValue;
	}
	
	/*
	 *  (non-Javadoc)
	 * @see org.openoffice.ide.eclipse.core.gui.rows.LabeledRow#getValue()
	 */
	public String getValue() {
		return Boolean.toString(mValue);
	}

	/*
	 *  (non-Javadoc)
	 * @see org.openoffice.ide.eclipse.core.gui.rows.LabeledRow#setEnabled(boolean)
	 */
	public void setEnabled(boolean enabled) {
		((Button)mLabel).setEnabled(enabled);
	}
}