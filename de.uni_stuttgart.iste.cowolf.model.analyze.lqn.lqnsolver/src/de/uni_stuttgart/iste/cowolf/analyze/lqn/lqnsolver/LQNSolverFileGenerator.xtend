package de.uni_stuttgart.iste.cowolf.analyze.lqn.lqnsolver

import de.uni_stuttgart.iste.cowolf.model.LqnCore.LQN
import java.util.Map

interface LQNSolverFileGenerator {
	def CharSequence doGenerateLQNSolverFile(LQN root, Map<String, Object> parameters);
}