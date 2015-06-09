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
import org.structome.core.Relation;

public class SimpleGraph<N extends Artefact, E extends Relation<N>> implements Graph<N, E> {
	private Map<String, N> nodes = new HashMap<String, N>();
	private Map<String, Set<E>> outboundRelations = new HashMap<String, Set<E>>();

	@Override
	public Collection<N> artefacts() {
		return nodes.values();
	}

	@Override
	public void addArtefact(final N _artefact) {
		nodes.put(_artefact.getId(), _artefact);
	}

	@Override
	public void addRelation(E _relation) {
		String _sourceId = _relation.getSource().getId();

		Set<E> _relations = outboundRelations.get(_sourceId);

		if (_relations == null) {
			_relations = new HashSet<E>();

			outboundRelations.put(_sourceId, _relations);
		}

		_relations.add(_relation);
	}

	@Override
	public boolean hasRelations(String _id) {
		return outboundRelations.get(_id) != null;
	}

	@Override
	public Collection<E> getRelationsFor(String _id) {
		return outboundRelations.get(_id);
	}

	@Override
	public N getArtefact(String _id) {
		return nodes.get(_id);
	}

}
