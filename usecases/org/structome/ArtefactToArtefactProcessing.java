package org.structome;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.structome.core.ArtefactFactory;
import org.structome.core.ArtefactProcessor;
import org.structome.core.Graph;
import org.structome.core.GraphBuilder;
import org.structome.impl.SimpleGraph;
import org.structome.impl.SimpleRelation;

public class ArtefactToArtefactProcessing {
	Collection<SourceArtefact> _artefacts = new ArrayList<SourceArtefact>();

	@Before
	public void setUp() {
		SourceArtefact _s0 = new SourceArtefact("0", 100);
		SourceArtefact _s4 = new SourceArtefact("4", 104);
		_artefacts.add(_s0);
		_artefacts.add(new SourceArtefact("1", 101));
		_artefacts.add(new SourceArtefact("2", 102));
		_artefacts.add(new SourceArtefact("3", 103));
		_artefacts.add(_s4);
	}

	@Test
	public void artefactToRepresentationToArtefact() {
		ArtefactProcessor<SourceArtefact, SourceArtefactRepresentation> _processor = new ArtefactProcessor<SourceArtefact, SourceArtefactRepresentation>() {

			@Override
			public Collection<SourceArtefactRepresentation> process(Collection<SourceArtefact> _artefacts) {
				List<SourceArtefactRepresentation> _list = new ArrayList<SourceArtefactRepresentation>();

				for (SourceArtefact _artefact : _artefacts) {
					_list.add(new SourceArtefactRepresentation(_artefact));
				}

				return _list;
			}

		};

		GraphBuilder<TargetArtefact, SimpleRelation, SourceArtefactRepresentation> _builder = new GraphBuilder<TargetArtefact, SimpleRelation, SourceArtefactRepresentation>() {

			@Override
			public Graph<TargetArtefact, SimpleRelation> buildFrom(
					Collection<SourceArtefactRepresentation> _sources,
					ArtefactFactory<TargetArtefact, SourceArtefactRepresentation> _factory) {

				Graph<TargetArtefact, SimpleRelation> _graph = new SimpleGraph<TargetArtefact, SimpleRelation>();

				for (SourceArtefactRepresentation _rep : _sources) {
					TargetArtefact _artefact = _factory.createArtefact(_rep);

					_graph.addArtefact(_artefact);
				}

				TargetArtefact _s0 = _graph.getArtefact("0");
				TargetArtefact _s4 = _graph.getArtefact("4");
				_graph.createRelation(_s0, _s4);

				return _graph;
			}

		};

		ArtefactFactory<TargetArtefact, SourceArtefactRepresentation> _factory = new ArtefactFactory<TargetArtefact, SourceArtefactRepresentation>() {

			@Override
			public TargetArtefact createArtefact(SourceArtefactRepresentation _artefactRepresentation) {
				String _id = _artefactRepresentation.getId();
				String _number = _artefactRepresentation.getNumber();
				return new TargetArtefact(_id, _number);
			}
		};

		Graph<TargetArtefact, SimpleRelation> _graph = _builder.buildFrom(_processor.process(_artefacts),
				_factory);

		assertEquals(5, _graph.artefacts().size());
		assertEquals(1, _graph.getRelationsFor("0").size());
		assertTrue(_graph.getRelationsFor("0").contains(_graph.getArtefact("4")));
	}

	@Test
	public void artefactToArtefact() {
		GraphBuilder<TargetArtefact, SimpleRelation, SourceArtefact> _builder = new GraphBuilder<TargetArtefact, SimpleRelation, SourceArtefact>() {

			@Override
			public Graph<TargetArtefact, SimpleRelation> buildFrom(Collection<SourceArtefact> _sources,
					ArtefactFactory<TargetArtefact, SourceArtefact> _factory) {

				Graph<TargetArtefact, SimpleRelation> _graph = new SimpleGraph<TargetArtefact, SimpleRelation>();

				for (SourceArtefact _rep : _sources) {
					TargetArtefact _artefact = _factory.createArtefact(_rep);

					_graph.addArtefact(_artefact);
				}

				TargetArtefact _s0 = _graph.getArtefact("0");
				TargetArtefact _s4 = _graph.getArtefact("4");
				_graph.createRelation(_s0, _s4);

				return _graph;
			}

		};

		ArtefactFactory<TargetArtefact, SourceArtefact> _factory = new ArtefactFactory<TargetArtefact, SourceArtefact>() {

			@Override
			public TargetArtefact createArtefact(SourceArtefact _sourceArtefact) {
				return new TargetArtefact(_sourceArtefact.getId(), String.valueOf(_sourceArtefact
						.getANumber()));
			}
		};

		Graph<TargetArtefact, SimpleRelation> _graph = _builder.buildFrom(_artefacts, _factory);

		assertEquals(5, _graph.artefacts().size());
		assertEquals(1, _graph.getRelationsFor("0").size());
		assertTrue(_graph.getRelationsFor("0").contains(_graph.getArtefact("4")));
	}
}
