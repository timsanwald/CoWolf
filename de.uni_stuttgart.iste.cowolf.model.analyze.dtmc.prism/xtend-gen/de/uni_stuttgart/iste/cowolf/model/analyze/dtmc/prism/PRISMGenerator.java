package de.uni_stuttgart.iste.cowolf.model.analyze.dtmc.prism;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import de.uni_stuttgart.iste.cowolf.model.dtmc.DTMC;
import de.uni_stuttgart.iste.cowolf.model.dtmc.Label;
import de.uni_stuttgart.iste.cowolf.model.dtmc.State;
import de.uni_stuttgart.iste.cowolf.model.dtmc.Transition;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.Functions.Function2;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class PRISMGenerator {
  private HashMap<Object, Object> idToIntMap = CollectionLiterals.<Object, Object>newHashMap();
  
  public CharSequence generatePM(final Resource resource) {
    CharSequence _xifexpression = null;
    boolean _and = false;
    EList<EObject> _contents = resource.getContents();
    int _size = _contents.size();
    boolean _greaterThan = (_size > 0);
    if (!_greaterThan) {
      _and = false;
    } else {
      EList<EObject> _contents_1 = resource.getContents();
      EObject _get = _contents_1.get(0);
      _and = (_get instanceof DTMC);
    }
    if (_and) {
      CharSequence _xblockexpression = null;
      {
        EList<EObject> _contents_2 = resource.getContents();
        EObject _get_1 = _contents_2.get(0);
        DTMC e = ((DTMC) _get_1);
        this.addStatesToMap(e);
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("// This model is automatically generated from a CoWolf DTMC model.");
        _builder.newLine();
        _builder.append("// Please use CoWolf to make any changes to the model.");
        _builder.newLine();
        CharSequence _compilePM = this.compilePM(e);
        _builder.append(_compilePM, "");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        CharSequence _generateLabels = this.generateLabels(resource);
        _builder.append(_generateLabels, "");
        _xblockexpression = _builder;
      }
      _xifexpression = _xblockexpression;
    } else {
      StringConcatenation _builder = new StringConcatenation();
      _xifexpression = _builder;
    }
    return _xifexpression;
  }
  
  public CharSequence generatePCTL(final Resource resource, final Set<State> analyzeStates, final Set<String> analyzeLabels) {
    String result = "";
    boolean _and = false;
    EList<EObject> _contents = resource.getContents();
    int _size = _contents.size();
    boolean _greaterThan = (_size > 0);
    if (!_greaterThan) {
      _and = false;
    } else {
      EList<EObject> _contents_1 = resource.getContents();
      EObject _get = _contents_1.get(0);
      _and = (_get instanceof DTMC);
    }
    if (_and) {
      EList<EObject> _contents_2 = resource.getContents();
      EObject _get_1 = _contents_2.get(0);
      DTMC e = ((DTMC) _get_1);
      this.addStatesToMap(e);
      for (final State state : analyzeStates) {
        EList<State> _states = e.getStates();
        boolean _contains = _states.contains(state);
        if (_contains) {
          String _result = result;
          CharSequence _intState = this.getIntState(state);
          String _plus = ("P=? [ F s=" + _intState);
          String _plus_1 = (_plus + " ]\n");
          result = (_result + _plus_1);
        }
      }
      for (final String label : analyzeLabels) {
        EList<State> _states_1 = e.getStates();
        final Function1<State, EList<Label>> _function = new Function1<State, EList<Label>>() {
          public EList<Label> apply(final State s) {
            return s.getLabels();
          }
        };
        List<EList<Label>> _map = ListExtensions.<State, EList<Label>>map(_states_1, _function);
        Iterable<Label> _flatten = Iterables.<Label>concat(_map);
        final Function1<Label, Boolean> _function_1 = new Function1<Label, Boolean>() {
          public Boolean apply(final Label l) {
            String _name = l.getName();
            return Boolean.valueOf(_name.equals(label));
          }
        };
        boolean _exists = IterableExtensions.<Label>exists(_flatten, _function_1);
        if (_exists) {
          String _result_1 = result;
          String _replaceAll = label.replaceAll("[^a-zA-Z0-9_]", "_");
          String _plus_2 = ("P=? [ F \"" + _replaceAll);
          String _plus_3 = (_plus_2 + "\" ]\n");
          result = (_result_1 + _plus_3);
        }
      }
    } else {
      return "";
    }
    return result;
  }
  
  public CharSequence generateLabels(final Resource resource) {
    EList<EObject> _contents = resource.getContents();
    EObject _get = _contents.get(0);
    DTMC e = ((DTMC) _get);
    EList<State> _states = e.getStates();
    final Function1<State, EList<Label>> _function = new Function1<State, EList<Label>>() {
      public EList<Label> apply(final State s) {
        return s.getLabels();
      }
    };
    List<EList<Label>> _map = ListExtensions.<State, EList<Label>>map(_states, _function);
    Iterable<Label> labelMap = Iterables.<Label>concat(_map);
    HashMap<String, List<State>> _newHashMap = CollectionLiterals.<String, List<State>>newHashMap();
    final Function2<HashMap<String, List<State>>, Label, HashMap<String, List<State>>> _function_1 = new Function2<HashMap<String, List<State>>, Label, HashMap<String, List<State>>>() {
      public HashMap<String, List<State>> apply(final HashMap<String, List<State>> m, final Label l) {
        HashMap<String, List<State>> _xblockexpression = null;
        {
          String _name = l.getName();
          boolean _containsKey = m.containsKey(_name);
          boolean _not = (!_containsKey);
          if (_not) {
            String _name_1 = l.getName();
            LinkedList<State> _newLinkedList = CollectionLiterals.<State>newLinkedList();
            m.put(_name_1, _newLinkedList);
          }
          String _name_2 = l.getName();
          List<State> _get = m.get(_name_2);
          State _state = l.getState();
          _get.add(_state);
          _xblockexpression = m;
        }
        return _xblockexpression;
      }
    };
    HashMap<String, List<State>> labels = IterableExtensions.<Label, HashMap<String, List<State>>>fold(labelMap, _newHashMap, _function_1);
    Set<Map.Entry<String, List<State>>> _entrySet = labels.entrySet();
    final Function1<Map.Entry<String, List<State>>, String> _function_2 = new Function1<Map.Entry<String, List<State>>, String>() {
      public String apply(final Map.Entry<String, List<State>> l) {
        String _key = l.getKey();
        String _replaceAll = _key.replaceAll("[^a-zA-Z0-9_]", "_");
        String _plus = ("label \"" + _replaceAll);
        String _plus_1 = (_plus + "\" = ");
        List<State> _value = l.getValue();
        final Function1<State, CharSequence> _function = new Function1<State, CharSequence>() {
          public CharSequence apply(final State it) {
            CharSequence _intState = PRISMGenerator.this.getIntState(it);
            return ("s=" + _intState);
          }
        };
        String _join = IterableExtensions.<State>join(_value, "|", _function);
        String _plus_2 = (_plus_1 + _join);
        return (_plus_2 + ";");
      }
    };
    Iterable<String> _map_1 = IterableExtensions.<Map.Entry<String, List<State>>, String>map(_entrySet, _function_2);
    return IterableExtensions.join(_map_1, "\n");
  }
  
  public CharSequence addStatesToMap(final DTMC e) {
    StringConcatenation _builder = new StringConcatenation();
    HashMap<Object, Object> _newHashMap = CollectionLiterals.<Object, Object>newHashMap();
    _builder.append(this.idToIntMap = _newHashMap, "");
    _builder.newLineIfNotEmpty();
    {
      EList<State> _states = e.getStates();
      int _size = _states.size();
      int _minus = (_size - 1);
      IntegerRange _upTo = new IntegerRange(0, _minus);
      for(final Integer i : _upTo) {
        EList<State> _states_1 = e.getStates();
        State _get = _states_1.get((i).intValue());
        String _id = _get.getId();
        Object _put = this.idToIntMap.put(_id, i);
        _builder.append(_put, "");
        _builder.append("\t");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence getIntState(final State e) {
    try {
      CharSequence _xifexpression = null;
      String _id = e.getId();
      boolean _containsKey = this.idToIntMap.containsKey(_id);
      if (_containsKey) {
        StringConcatenation _builder = new StringConcatenation();
        String _id_1 = e.getId();
        Object _get = this.idToIntMap.get(_id_1);
        _builder.append(_get, "");
        _xifexpression = _builder;
      } else {
        throw new Exception("Unknown state found.");
      }
      return _xifexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public CharSequence getStart(final DTMC e) {
    StringConcatenation _builder = new StringConcatenation();
    State _initialState = e.getInitialState();
    CharSequence _intState = this.getIntState(_initialState);
    _builder.append(_intState, "");
    return _builder;
  }
  
  public CharSequence compilePM(final DTMC e) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("dtmc ");
    _builder.newLine();
    _builder.append("module ");
    {
      String _name = e.getName();
      boolean _isEmpty = _name.isEmpty();
      if (_isEmpty) {
        _builder.append("Model");
      } else {
        String _name_1 = e.getName();
        _builder.append(_name_1, "");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// As PRISM does not support names for states, they are translated to numbers.");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// The mapping is as follows.");
    _builder.newLine();
    {
      EList<State> _states = e.getStates();
      for(final State f : _states) {
        _builder.append("\t");
        _builder.append("// State \"");
        String _xifexpression = null;
        boolean _or = false;
        String _name_2 = f.getName();
        boolean _equals = Objects.equal(_name_2, null);
        if (_equals) {
          _or = true;
        } else {
          String _name_3 = f.getName();
          boolean _isEmpty_1 = _name_3.isEmpty();
          _or = _isEmpty_1;
        }
        if (_or) {
          _xifexpression = f.getId();
        } else {
          _xifexpression = f.getName();
        }
        _builder.append(_xifexpression, "\t");
        _builder.append("\" => ");
        CharSequence _intState = this.getIntState(f);
        _builder.append(_intState, "\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    {
      EList<State> _states_1 = e.getStates();
      int _size = _states_1.size();
      boolean _lessEqualsThan = (_size <= 1);
      if (_lessEqualsThan) {
        _builder.append("s : [0 .. 1] init 0;");
      } else {
        _builder.append("s : [0..");
        EList<State> _states_2 = e.getStates();
        int _size_1 = _states_2.size();
        int _minus = (_size_1 - 1);
        _builder.append(_minus, "\t");
        _builder.append("] init ");
        CharSequence _start = this.getStart(e);
        _builder.append(_start, "\t");
        _builder.append(";");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.newLine();
    {
      EList<State> _states_3 = e.getStates();
      for(final State f_1 : _states_3) {
        {
          EList<Transition> _outgoing = f_1.getOutgoing();
          int _size_2 = _outgoing.size();
          boolean _greaterThan = (_size_2 > 0);
          if (_greaterThan) {
            _builder.append("\t");
            _builder.append("// Transitions from state \"");
            String _xifexpression_1 = null;
            boolean _or_1 = false;
            String _name_4 = f_1.getName();
            boolean _equals_1 = Objects.equal(_name_4, null);
            if (_equals_1) {
              _or_1 = true;
            } else {
              String _name_5 = f_1.getName();
              boolean _isEmpty_2 = _name_5.isEmpty();
              _or_1 = _isEmpty_2;
            }
            if (_or_1) {
              _xifexpression_1 = f_1.getId();
            } else {
              _xifexpression_1 = f_1.getName();
            }
            _builder.append(_xifexpression_1, "\t");
            _builder.append("\":");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("[] s=");
            CharSequence _intState_1 = this.getIntState(f_1);
            _builder.append(_intState_1, "\t");
            _builder.append(" -> ");
            CharSequence _compilePM = this.compilePM(f_1);
            _builder.append(_compilePM, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.newLine();
    _builder.append("endmodule");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence compilePM(final State e) {
    CharSequence _xifexpression = null;
    EList<Transition> _outgoing = e.getOutgoing();
    int _size = _outgoing.size();
    boolean _greaterThan = (_size > 1);
    if (_greaterThan) {
      StringConcatenation _builder = new StringConcatenation();
      {
        EList<Transition> _outgoing_1 = e.getOutgoing();
        int _size_1 = _outgoing_1.size();
        int _minus = (_size_1 - 2);
        IntegerRange _upTo = new IntegerRange(0, _minus);
        for(final Integer i : _upTo) {
          EList<Transition> _outgoing_2 = e.getOutgoing();
          Transition _get = _outgoing_2.get((i).intValue());
          float _prob = _get.getProb();
          _builder.append(_prob, "");
          _builder.append(" : (s\'=");
          EList<Transition> _outgoing_3 = e.getOutgoing();
          Transition _get_1 = _outgoing_3.get((i).intValue());
          State _to = _get_1.getTo();
          CharSequence _intState = this.getIntState(_to);
          _builder.append(_intState, "");
          _builder.append(") + ");
        }
      }
      EList<Transition> _outgoing_4 = e.getOutgoing();
      EList<Transition> _outgoing_5 = e.getOutgoing();
      int _size_2 = _outgoing_5.size();
      int _minus_1 = (_size_2 - 1);
      Transition _get_2 = _outgoing_4.get(_minus_1);
      float _prob_1 = _get_2.getProb();
      _builder.append(_prob_1, "");
      _builder.append(" : (s\'=");
      EList<Transition> _outgoing_6 = e.getOutgoing();
      EList<Transition> _outgoing_7 = e.getOutgoing();
      int _size_3 = _outgoing_7.size();
      int _minus_2 = (_size_3 - 1);
      Transition _get_3 = _outgoing_6.get(_minus_2);
      State _to_1 = _get_3.getTo();
      CharSequence _intState_1 = this.getIntState(_to_1);
      _builder.append(_intState_1, "");
      _builder.append(");");
      _xifexpression = _builder;
    } else {
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("(s\'=");
      EList<Transition> _outgoing_8 = e.getOutgoing();
      Transition _get_4 = _outgoing_8.get(0);
      State _to_2 = _get_4.getTo();
      CharSequence _intState_2 = this.getIntState(_to_2);
      _builder_1.append(_intState_2, "");
      _builder_1.append(");");
      _xifexpression = _builder_1;
    }
    return _xifexpression;
  }
}
