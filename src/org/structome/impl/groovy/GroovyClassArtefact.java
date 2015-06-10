/*************************************************************************** 
   Copyright 2015 Federico Ricca

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 ***************************************************************************/
package org.structome.impl.groovy;

import java.util.HashMap;
import java.util.Map;

import org.structome.core.Artefact;
import org.structome.util.ClassNameUtils;

public class GroovyClassArtefact implements Artefact {

	private String className;
	private String packageName;
	private String superClassName;
	private String superClassPackageName;

	private Map<String, String> imports = new HashMap<String, String>();

	@Override
	public String getId() {
		return packageName + "." + className;
	}

	public void setClassName(String _nameWithoutPackage) {
		className = _nameWithoutPackage;
	}

	public void addGenerics(String nameWithoutPackage) {
		// TODO add class generic
	}

	public void setSuperClassPackageName(final String _pkgName) {
		if (_pkgName.startsWith("java.lang")) {
			superClassName = null;
			superClassPackageName = null;

			return;
		}

		superClassPackageName = _pkgName;
	}

	public void setSuperClassName(final String _superClassName) {
		if (_superClassName.startsWith("java.lang")) {
			superClassName = null;
			superClassPackageName = null;

			return;
		}

		final String[] _name = ClassNameUtils.split(_superClassName);

		this.setSuperClassPackageName(_name[0]);
		superClassName = _name[1];
	}

	public void addSuperClassGenerics(String nameWithoutPackage) {
		// TODO add super class generics

	}

	public void addImport(String _import) {
		final int _iIndex = _import.lastIndexOf(".");

		if (_iIndex == -1) {
			return;
		}

		final String _pkgName = _import.substring(0, _iIndex);
		final String _className = _import.substring(_iIndex + 1, _import.length());

		imports.put(_className, _pkgName);
	}

	public void addAnnotation(String _annotation) {
		// TODO add annotation

	}

	public void setPackageName(String name) {
		packageName = name;
	}

	public String getSuperClassName() {
		return superClassName;
	}

	public String getSuperClassPackageName() {
		return superClassPackageName;
	}

	public String getPackageName() {
		return packageName;
	}

	public String findImportByClass(String _className) {
		return imports.get(_className);
	}

	public String getSuperClassId() {
		if (this.getSuperClassName() == null) {
			return null;
		}
		
		return this.getSuperClassPackageName() + "." + this.getSuperClassName();
	}
}
