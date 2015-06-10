package org.structome.util;

public class ClassNameUtils {

	public final static String[] split(final String _fqcn) {
		final String[] _name = new String[2];

		int _iIndex = _fqcn.lastIndexOf(".");

		if (_iIndex == -1) {
			_name[0] = "";
			_name[1] = _fqcn;
		} else {
			_name[0] = _fqcn.substring(0, _iIndex);
			_name[1] = _fqcn.substring(_iIndex + 1, _fqcn.length());
		}

		return _name;
	}

}
