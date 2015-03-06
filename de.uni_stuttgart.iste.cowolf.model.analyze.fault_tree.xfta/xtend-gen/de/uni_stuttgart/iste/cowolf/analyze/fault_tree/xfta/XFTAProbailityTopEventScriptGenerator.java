package de.uni_stuttgart.iste.cowolf.analyze.fault_tree.xfta;

import de.uni_stuttgart.iste.cowolf.analyze.fault_tree.xfta.XFTAGenerator;
import de.uni_stuttgart.iste.cowolf.model.fault_tree.FaultTree;
import de.uni_stuttgart.iste.cowolf.model.fault_tree.Hazard;
import java.util.Map;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class XFTAProbailityTopEventScriptGenerator implements XFTAGenerator {
  public CharSequence doGenerateXFTAFile(final FaultTree root, final Map<String, Object> parameters) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _xFTAProbabiltyTopEventTemplate = this.xFTAProbabiltyTopEventTemplate(root, parameters);
    _builder.append(_xFTAProbabiltyTopEventTemplate, "");
    return _builder;
  }
  
  public CharSequence xFTAProbabiltyTopEventTemplate(final FaultTree r, final Map<String, Object> parameters) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?xml version=\"1.0\"?>");
    _builder.newLine();
    _builder.append("<!DOCTYPE xfta>");
    _builder.newLine();
    _builder.append("<xfta>");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("<load>");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<model input=\"");
    Object _get = parameters.get("pathToInputXFTAFile");
    _builder.append(_get, "    ");
    _builder.append("\" />");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("</load>");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("<build>");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<minimal-cutsets top-event=\"");
    Hazard _hazard = r.getHazard();
    String _id = _hazard.getId();
    _builder.append(_id, "    ");
    _builder.append("\" handle=\"MCS\" />");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("</build>");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("<compute>");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<importance-factors top-event=\"");
    Hazard _hazard_1 = r.getHazard();
    String _id_1 = _hazard_1.getId();
    _builder.append(_id_1, "    ");
    _builder.append("\" handle=\"MCS\" output=\"");
    Object _get_1 = parameters.get("pathToOutputXFTAFile");
    _builder.append(_get_1, "    ");
    _builder.append("\" />");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("</compute>");
    _builder.newLine();
    _builder.append("</xfta>");
    _builder.newLine();
    return _builder;
  }
}
