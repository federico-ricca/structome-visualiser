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
package org.structome.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.structome.core.Artefact;
import org.structome.core.Graph;

public class SimpleGraph implements Graph {
	private Map<String, Artefact> nodes = new HashMap<String, Artefact>();
	private Map<String, Set<Artefact>> outboundRelations = new HashMap<String, Set<Artefact>>();

	@Override
	public Collection<Artefact> artefacts() {
		return nodes.values();
	}

	@Override
	public void addArtefact(final Artefact _artefact) {
		nodes.put(_artefact.getId(), _artefact);
	}

	@Override
	public void createRelation(Artefact _artefactA, Artefact _artefactB) {
		String _sourceId = _artefactA.getId();

		Set<Artefact> _referencedArtefacts = outboundRelations.get(_sourceId);

		if (_referencedArtefacts == null) {
			_referencedArtefacts = new HashSet<Artefact>();

			outboundRelations.put(_sourceId, _referencedArtefacts);
		}

		_referencedArtefacts.add(_artefactB);
	}

	@Override
	public boolean hasRelations(String _id) {
		return outboundRelations.get(_id) != null;
	}

	@Override
	public Collection<Artefact> getRelationsFor(String _id) {
		return outboundRelations.get(_id);
	}

}
