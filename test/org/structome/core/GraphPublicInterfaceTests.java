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

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.structome.core.Artefact;
import org.structome.core.Graph;
import org.structome.core.GraphLoader;
import org.structome.impl.SimpleArtefact;
import org.structome.impl.SimpleGraph;

public class GraphPublicInterfaceTests {

	@Test
	public void testNoDuplicateNodes() {
		Graph _graph = new SimpleGraph();
		Artefact _a = new SimpleArtefact("A");
		Artefact _b = new SimpleArtefact("A");

		_graph.addArtefact(_a);
		_graph.addArtefact(_b);

		assertEquals(1, _graph.artefacts().size());
	}

	/*
	 * This test is designed to drive the public interface of Graph, Artefact,
	 * Relation and GraphLoader.
	 * 
	 * Test a graph of the form:
	 * 
	 * A -> B A -> C B -> D D -> E
	 */
	@Test
	public void testLoadGraph() throws IOException {
		GraphLoader<String> _graphLoader = new GraphLoader<String>() {
			@Override
			public void loadInto(Graph graph, ArtefactFactory<String> _factory) throws IOException {
				Artefact _A = _factory.createArtefact("A");
				graph.addArtefact(_A);

				Artefact _B = _factory.createArtefact("B");
				graph.addArtefact(_B);

				Artefact _C = _factory.createArtefact("C");
				graph.addArtefact(_C);

				Artefact _D = _factory.createArtefact("D");
				graph.addArtefact(_D);

				Artefact _E = _factory.createArtefact("E");
				graph.addArtefact(_E);

				graph.createRelation(_A, _B);
				graph.createRelation(_A, _C);
				graph.createRelation(_B, _D);
				graph.createRelation(_D, _E);
			}
		};

		ArtefactFactory<String> _factory = new ArtefactFactory<String>() {

			@Override
			public Artefact createArtefact(String _artefactRepresentation) {
				return new SimpleArtefact(_artefactRepresentation);
			}
		};

		Graph graph = new SimpleGraph();

		_graphLoader.loadInto(graph, _factory);

		assertEquals(5, graph.artefacts().size());
		assertEquals(2, graph.getRelationsFor("A").size());
	}
}
