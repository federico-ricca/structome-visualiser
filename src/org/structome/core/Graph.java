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
package org.structome.core;

import java.util.Collection;

public interface Graph<N extends Artefact, E extends Relation> {

	public Collection<N> artefacts();

	public void createRelation(final N _artefactA, final N _artefactB);

	public void addArtefact(final N _artefact);

	public Collection<N> getRelationsFor(String _id);

	public boolean hasRelations(String _id);

	public N getArtefact(String _id);

}
