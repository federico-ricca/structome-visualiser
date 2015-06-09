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

import java.util.Collection;
import java.util.HashMap;

import org.structome.core.Graph;

import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;

public class GroovyClassDependencyGraph implements Graph<GroovyClassArtefact, ClassReferenceRelation> {
	private UndirectedSparseMultigraph<GroovyClassArtefact, ClassReferenceRelation> graph;
	private HashMap<String, GroovyClassArtefact> artefactMap;

	public GroovyClassDependencyGraph() {
		graph = new UndirectedSparseMultigraph<GroovyClassArtefact, ClassReferenceRelation>();
		artefactMap = new HashMap<String, GroovyClassArtefact>();
	}

	@Override
	public Collection<GroovyClassArtefact> artefacts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addRelation(ClassReferenceRelation _relation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addArtefact(GroovyClassArtefact _artefact) {
		graph.addVertex(_artefact);
		artefactMap.put(_artefact.getId(), _artefact);
	}

	@Override
	public Collection<ClassReferenceRelation> getRelationsFor(String _id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasRelations(String _id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GroovyClassArtefact getArtefact(String _id) {
		return artefactMap.get(_id);
	}

}
