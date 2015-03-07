package de.uni_stuttgart.iste.cowolf.analyze.fault_tree.xfta;

import de.uni_stuttgart.iste.cowolf.model.fault_tree.FaultTree;
import java.util.Map;

@SuppressWarnings("all")
public interface XFTAGenerator {
  public abstract CharSequence doGenerateXFTAFile(final FaultTree root, final Map<String, Object> parameters);
}