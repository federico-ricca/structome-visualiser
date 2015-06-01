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
package org.structome;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.structome.core.ArtefactFactory;
import org.structome.core.Graph;
import org.structome.core.GraphBuilder;
import org.structome.impl.groovy.ClassReferenceRelation;
import org.structome.impl.groovy.GroovyClassArtefact;
import org.structome.impl.groovy.GroovyClassArtefactFactory;
import org.structome.impl.groovy.GroovyClassDependencyGraph;
import org.structome.impl.groovy.GroovyFileArtefact;

public class GroovyClassArtefactsProcessing {
	static String[] sourceCodeA = { 
		"package some.package;", 
		"class BaseClass {",
		"}" };

	static String[] sourceCodeB = { 
		"package some.package;", 
		"class TestClassA extends BaseClass {",
		"	public List<ClassB> myList;",
		"	public ReturnValue testMethod(Set<ClassC> _set) {",
		"		Map<ClassD, ClassE> _map = [:];",
		"		SomeClass.someStaticMethod(0);",
		"		if (_set instanceof AnotherClass) {",
		"			BaseClass p = (SomeOtherClass) _set;", 
		"		}",
		"		return null;",
		"	}",
		"}" };

	static String[] sourceCodeC = { 
		"package some.package;", 
		"class AnotherClass extends BaseClass {",
		"	public void testMethod() {",
		"	}",
		"}" };

	static String[][] sources = { sourceCodeA , sourceCodeB, sourceCodeC };
	
	Collection<GroovyFileArtefact> sourceArtefacts;

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void setup() throws IOException {
		sourceArtefacts = new ArrayList<GroovyFileArtefact>();

		folder.create();

		for (String[] _sourceCode : sources) {
			// Create temporary folder and file for source code
			File _f = folder.newFile();
			
			PrintStream _ps = new PrintStream(_f);
			for (String s : _sourceCode) {
				_ps.println(s);
			}
			_ps.close();
			
			sourceArtefacts.add(new GroovyFileArtefact(_f));
		}

	}

	@Test
	public void groovyFileArtefactToGroovyClassArtefact() {
		GraphBuilder<GroovyClassArtefact, ClassReferenceRelation, GroovyFileArtefact> _builder = new GraphBuilder<GroovyClassArtefact, ClassReferenceRelation, GroovyFileArtefact>() {

			@Override
			public Graph<GroovyClassArtefact, ClassReferenceRelation> buildFrom(GroovyFileArtefact _source,
					ArtefactFactory<GroovyClassArtefact, GroovyFileArtefact> _factory) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Graph<GroovyClassArtefact, ClassReferenceRelation> buildFrom(
					Collection<GroovyFileArtefact> _sources,
					ArtefactFactory<GroovyClassArtefact, GroovyFileArtefact> _factory) {
				GroovyClassDependencyGraph _graph = new GroovyClassDependencyGraph();
				
				for (GroovyFileArtefact _source : _sources) {
					GroovyClassArtefact _classArtefact = _factory.createArtefact(_source);
					_graph.addArtefact(_classArtefact);
				}
				return _graph;
			}
		};

		GroovyClassArtefactFactory _factory = new GroovyClassArtefactFactory();
		
		Graph<GroovyClassArtefact, ClassReferenceRelation> _graph = _builder.buildFrom(sourceArtefacts, _factory);
		
		assertNotNull(_graph);
		//assertNotNull(_graph.getArtefact("some.package.BaseClass"));
		//assertNotNull(_graph.getRelationsFor("some.package.BaseClass"));
		//assertEquals(2, _graph.getRelationsFor("some.package.BaseClass"));
	}
}