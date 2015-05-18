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
package org.structome.io;

import java.io.File;
import java.io.IOException;

import org.structome.core.Artefact;
import org.structome.core.ArtefactFactory;
import org.structome.core.Graph;
import org.structome.core.GraphLoader;
import org.structome.core.Relation;
import org.structome.util.TextFileReader;
import org.structome.util.TextFileReader.LineVisitor;

public class CSVFileGraphLoader<N extends Artefact, E extends Relation> implements GraphLoader<N, E, String> {
	private File csvFile;

	public CSVFileGraphLoader(String _filename) {
		csvFile = new File(_filename);
	}

	public CSVFileGraphLoader(File _file) {
		csvFile = _file;
	}

	@Override
	public void loadInto(final Graph<N, E> _graph, final ArtefactFactory<N, String> _factory)
			throws IOException {
		TextFileReader _reader = new TextFileReader(csvFile);

		_reader.readWith(new LineVisitor() {

			@Override
			public void visit(String _line, int _lineNumber) {
				int _iIndexOf = _line.indexOf(",");

				if (_iIndexOf != -1) {
					String _sourceElement = _line.substring(0, _iIndexOf).trim();
					String _destElement = _line.substring(_iIndexOf + 1, _line.length()).trim();

					N _source = _factory.createArtefact(_sourceElement);
					N _dest = _factory.createArtefact(_destElement);

					_graph.addArtefact(_source);
					_graph.addArtefact(_dest);
					_graph.createRelation(_source, _dest);
				} else {
					String _element = _line.trim();

					N _artefact = _factory.createArtefact(_element);

					_graph.addArtefact(_artefact);
				}
			}
		});
	}

}
