package de.uni_stuttgart.iste.cowolf.analyze.lqn.lqnsolver;

import de.uni_stuttgart.iste.cowolf.model.LqnCore.LQN;
import java.util.Map;

@SuppressWarnings("all")
public interface LQNSolverFileGenerator {
  public abstract CharSequence doGenerateLQNSolverFile(final LQN root, final Map<String, Object> parameters);
}
