package de.uni_stuttgart.iste.cowolf.analyze.fault_tree.xfta

import java.util.Map
import de.uni_stuttgart.iste.cowolf.model.fault_tree.FaultTree

interface XFTAGenerator {

	def CharSequence doGenerateXFTAFile(FaultTree root, Map<String, Object> parameters);
}
