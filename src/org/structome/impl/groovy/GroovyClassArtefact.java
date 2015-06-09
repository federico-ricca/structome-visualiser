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

import org.structome.core.Artefact;

public class GroovyClassArtefact implements Artefact {

	private String className;
	private String superClassName;
	private String packageName;
	
	@Override
	public final String getId() {
		return packageName + "." + className;
	}

	public void setClassName(String _nameWithoutPackage) {
		className = _nameWithoutPackage;
	}

	public void addGenerics(String nameWithoutPackage) {
		// TODO add class generic
	}

	public void setSuperClassName(String _nameWithoutPackage) {
		superClassName = _nameWithoutPackage;
	}

	public void addSuperClassGenerics(String nameWithoutPackage) {
		// TODO add super class generics
		
	}

	public void addImport(String _import) {
		// TODO add import
		
	}

	public void addAnnotation(String _annotation) {
		// TODO add annotation
		
	}

	public void setPackageName(String name) {
		packageName = name;
	}

}
