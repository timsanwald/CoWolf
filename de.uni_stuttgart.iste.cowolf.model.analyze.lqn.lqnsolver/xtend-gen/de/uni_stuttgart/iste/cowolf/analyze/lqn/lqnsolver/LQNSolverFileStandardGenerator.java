package de.uni_stuttgart.iste.cowolf.analyze.lqn.lqnsolver;

import com.google.common.base.Objects;
import com.google.common.collect.Iterators;
import de.uni_stuttgart.iste.cowolf.analyze.lqn.lqnsolver.LQNSolverFileGenerator;
import de.uni_stuttgart.iste.cowolf.model.LqnCore.ActivityDefType;
import de.uni_stuttgart.iste.cowolf.model.LqnCore.ActivityMakingCallType;
import de.uni_stuttgart.iste.cowolf.model.LqnCore.ActivityPhasesType;
import de.uni_stuttgart.iste.cowolf.model.LqnCore.CallOrderType;
import de.uni_stuttgart.iste.cowolf.model.LqnCore.EntryActivityGraph;
import de.uni_stuttgart.iste.cowolf.model.LqnCore.EntryMakingCallType;
import de.uni_stuttgart.iste.cowolf.model.LqnCore.EntryType;
import de.uni_stuttgart.iste.cowolf.model.LqnCore.LQN;
import de.uni_stuttgart.iste.cowolf.model.LqnCore.LqnModelType;
import de.uni_stuttgart.iste.cowolf.model.LqnCore.PhaseActivities;
import de.uni_stuttgart.iste.cowolf.model.LqnCore.ProcessorType;
import de.uni_stuttgart.iste.cowolf.model.LqnCore.SchedulingType;
import de.uni_stuttgart.iste.cowolf.model.LqnCore.SolverParamsType;
import de.uni_stuttgart.iste.cowolf.model.LqnCore.TaskSchedulingType;
import de.uni_stuttgart.iste.cowolf.model.LqnCore.TaskType;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Pair;

@SuppressWarnings("all")
public class LQNSolverFileStandardGenerator implements LQNSolverFileGenerator {
  private final Map<String, String> processorSchedules = Collections.<String, String>unmodifiableMap(CollectionLiterals.<String, String>newHashMap(Pair.<String, String>of("fcfs", "f"), Pair.<String, String>of("ps", "s"), Pair.<String, String>of("pp", "p"), Pair.<String, String>of("rand", "r"), Pair.<String, String>of("hol", "h"), Pair.<String, String>of("ps-hol", "h"), Pair.<String, String>of("ps-pp", "s")));
  
  private final Map<String, String> taskSchedules = Collections.<String, String>unmodifiableMap(CollectionLiterals.<String, String>newHashMap(Pair.<String, String>of("ref", "r"), Pair.<String, String>of("fcfs", "f"), Pair.<String, String>of("pri", ""), Pair.<String, String>of("hol", "h"), Pair.<String, String>of("burst", "b"), Pair.<String, String>of("poll", "p"), Pair.<String, String>of("inf", "i"), Pair.<String, String>of("semaphore", "S")));
  
  private final int nophases = 3;
  
  private Map<String, String> entries;
  
  public CharSequence doGenerateLQNSolverFile(final LQN root, final Map<String, Object> parameters) {
    this.init(root);
    StringConcatenation _builder = new StringConcatenation();
    LqnModelType _lqnModel = root.getLqnModel();
    CharSequence _lqnStandardTemplate = this.lqnStandardTemplate(_lqnModel, parameters);
    _builder.append(_lqnStandardTemplate, "");
    return _builder;
  }
  
  public void init(final LQN root) {
    HashMap<String, String> _hashMap = new HashMap<String, String>();
    this.entries = _hashMap;
    TreeIterator<EObject> _eAllContents = root.eAllContents();
    Iterator<EntryType> _filter = Iterators.<EntryType>filter(_eAllContents, EntryType.class);
    Iterable<EntryType> _iterable = IteratorExtensions.<EntryType>toIterable(_filter);
    for (final EntryType e : _iterable) {
      boolean _and = false;
      String _name = e.getName();
      boolean _notEquals = (!Objects.equal(_name, null));
      if (!_notEquals) {
        _and = false;
      } else {
        String _id = e.getId();
        boolean _notEquals_1 = (!Objects.equal(_id, null));
        _and = _notEquals_1;
      }
      if (_and) {
        String _name_1 = e.getName();
        String _id_1 = e.getId();
        this.entries.put(_name_1, _id_1);
      }
    }
  }
  
  public CharSequence lqnStandardTemplate(final LqnModelType r, final Map<String, Object> parameters) {
    StringConcatenation _builder = new StringConcatenation();
    SolverParamsType _solverParams = r.getSolverParams();
    CharSequence _processSolverParams = this.processSolverParams(_solverParams);
    _builder.append(_processSolverParams, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("P 0");
    _builder.newLine();
    {
      EList<ProcessorType> _processor = r.getProcessor();
      for(final ProcessorType p : _processor) {
        CharSequence _processProcessor = this.processProcessor(p);
        _builder.append(_processProcessor, "");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("-1");
    _builder.newLine();
    _builder.newLine();
    _builder.append("T 0");
    _builder.newLine();
    {
      TreeIterator<EObject> _eAllContents = r.eAllContents();
      Iterator<TaskType> _filter = Iterators.<TaskType>filter(_eAllContents, TaskType.class);
      List<TaskType> _list = IteratorExtensions.<TaskType>toList(_filter);
      for(final TaskType t : _list) {
        CharSequence _processTask = this.processTask(t);
        _builder.append(_processTask, "");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("-1");
    _builder.newLine();
    _builder.newLine();
    _builder.append("E 0");
    _builder.newLine();
    {
      TreeIterator<EObject> _eAllContents_1 = r.eAllContents();
      Iterator<EntryType> _filter_1 = Iterators.<EntryType>filter(_eAllContents_1, EntryType.class);
      List<EntryType> _list_1 = IteratorExtensions.<EntryType>toList(_filter_1);
      for(final EntryType e : _list_1) {
        CharSequence _processEntry = this.processEntry(e);
        _builder.append(_processEntry, "");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("-1");
    _builder.newLine();
    _builder.newLine();
    {
      TreeIterator<EObject> _eAllContents_2 = r.eAllContents();
      Iterator<TaskType> _filter_2 = Iterators.<TaskType>filter(_eAllContents_2, TaskType.class);
      List<TaskType> _list_2 = IteratorExtensions.<TaskType>toList(_filter_2);
      for(final TaskType t_1 : _list_2) {
        {
          TreeIterator<EObject> _eAllContents_3 = t_1.eAllContents();
          Iterator<ActivityDefType> _filter_3 = Iterators.<ActivityDefType>filter(_eAllContents_3, ActivityDefType.class);
          List<ActivityDefType> _list_3 = IteratorExtensions.<ActivityDefType>toList(_filter_3);
          for(final ActivityDefType a : _list_3) {
            _builder.append("A ");
            String _id = t_1.getId();
            _builder.append(_id, "");
            _builder.newLineIfNotEmpty();
            CharSequence _processActivities = this.processActivities(a);
            _builder.append(_processActivities, "");
            _builder.newLineIfNotEmpty();
            _builder.append("-1");
            _builder.newLine();
            _builder.newLine();
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence processSolverParams(final SolverParamsType solverParams) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("G \"LQN Model. Generated by CoWolf\"");
    _builder.newLine();
    Object _convVal = solverParams.getConvVal();
    _builder.append(_convVal, "");
    _builder.newLineIfNotEmpty();
    int _itLimit = solverParams.getItLimit();
    _builder.append(_itLimit, "");
    _builder.newLineIfNotEmpty();
    int _printInt = solverParams.getPrintInt();
    _builder.append(_printInt, "");
    _builder.newLineIfNotEmpty();
    Object _underrelaxCoeff = solverParams.getUnderrelaxCoeff();
    _builder.append(_underrelaxCoeff, "");
    _builder.newLineIfNotEmpty();
    _builder.append("-1");
    return _builder;
  }
  
  public CharSequence processProcessor(final ProcessorType processor) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("p ");
    String _id = processor.getId();
    String _beatifyString = this.beatifyString(_id);
    _builder.append(_beatifyString, "");
    _builder.append(" ");
    SchedulingType _scheduling = processor.getScheduling();
    String _name = _scheduling.getName();
    String _get = this.processorSchedules.get(_name);
    _builder.append(_get, "");
    _builder.append(" ");
    {
      BigInteger _multiplicity = processor.getMultiplicity();
      int _intValue = _multiplicity.intValue();
      boolean _greaterThan = (_intValue > 0);
      if (_greaterThan) {
        _builder.append("m ");
        BigInteger _multiplicity_1 = processor.getMultiplicity();
        _builder.append(_multiplicity_1, "");
      }
    }
    _builder.append(" ");
    {
      BigInteger _replication = processor.getReplication();
      int _intValue_1 = _replication.intValue();
      boolean _greaterThan_1 = (_intValue_1 > 0);
      if (_greaterThan_1) {
        _builder.append("r ");
        BigInteger _replication_1 = processor.getReplication();
        _builder.append(_replication_1, "");
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence processTask(final TaskType task) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("t ");
    String _id = task.getId();
    String _beatifyString = this.beatifyString(_id);
    _builder.append(_beatifyString, "");
    _builder.append(" ");
    TaskSchedulingType _scheduling = task.getScheduling();
    String _name = _scheduling.getName();
    String _get = this.taskSchedules.get(_name);
    _builder.append(_get, "");
    _builder.append(" ");
    {
      EList<EntryType> _entry = task.getEntry();
      boolean _hasElements = false;
      for(final EntryType e : _entry) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(" ", "");
        }
        String _id_1 = e.getId();
        String _beatifyString_1 = this.beatifyString(_id_1);
        _builder.append(_beatifyString_1, "");
      }
    }
    _builder.append(" -1 ");
    EObject _eContainer = task.eContainer();
    ProcessorType _cast = ProcessorType.class.cast(_eContainer);
    String _id_2 = _cast.getId();
    String _beatifyString_2 = this.beatifyString(_id_2);
    _builder.append(_beatifyString_2, "");
    {
      BigInteger _queueLength = task.getQueueLength();
      int _intValue = _queueLength.intValue();
      boolean _greaterThan = (_intValue > 0);
      if (_greaterThan) {
        BigInteger _queueLength_1 = task.getQueueLength();
        _builder.append(_queueLength_1, "");
      }
    }
    _builder.append("   ");
    {
      BigInteger _priority = task.getPriority();
      int _intValue_1 = _priority.intValue();
      boolean _greaterThan_1 = (_intValue_1 > 0);
      if (_greaterThan_1) {
        BigInteger _priority_1 = task.getPriority();
        _builder.append(_priority_1, "");
      }
    }
    _builder.append(" ");
    {
      Object _thinkTime = task.getThinkTime();
      boolean _notEquals = (!Objects.equal(_thinkTime, Integer.valueOf(0)));
      if (_notEquals) {
        Object _thinkTime_1 = task.getThinkTime();
        _builder.append(_thinkTime_1, "");
      }
    }
    _builder.append(" ");
    {
      BigInteger _multiplicity = task.getMultiplicity();
      int _intValue_2 = _multiplicity.intValue();
      boolean _greaterThan_2 = (_intValue_2 > 0);
      if (_greaterThan_2) {
        _builder.append("m ");
        BigInteger _multiplicity_1 = task.getMultiplicity();
        _builder.append(_multiplicity_1, "");
      }
    }
    _builder.append(" ");
    {
      BigInteger _replication = task.getReplication();
      int _intValue_3 = _replication.intValue();
      boolean _greaterThan_3 = (_intValue_3 > 0);
      if (_greaterThan_3) {
        _builder.append("r ");
        BigInteger _replication_1 = task.getReplication();
        _builder.append(_replication_1, "");
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence processEntry(final EntryType entry) {
    StringConcatenation _builder = new StringConcatenation();
    {
      Object _openArrivalRate = entry.getOpenArrivalRate();
      boolean _notEquals = (!Objects.equal(_openArrivalRate, null));
      if (_notEquals) {
        _builder.append("a ");
        String _id = entry.getId();
        String _beatifyString = this.beatifyString(_id);
        _builder.append(_beatifyString, "");
        _builder.append(" ");
        Object _openArrivalRate_1 = entry.getOpenArrivalRate();
        _builder.append(_openArrivalRate_1, "");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      EntryActivityGraph _entryActivityGraph = entry.getEntryActivityGraph();
      boolean _notEquals_1 = (!Objects.equal(_entryActivityGraph, null));
      if (_notEquals_1) {
        _builder.append("A ");
        String _id_1 = entry.getId();
        String _beatifyString_1 = this.beatifyString(_id_1);
        _builder.append(_beatifyString_1, "");
        _builder.append(" ");
        EntryActivityGraph _entryActivityGraph_1 = entry.getEntryActivityGraph();
        EList<ActivityDefType> _activity = _entryActivityGraph_1.getActivity();
        Iterator<ActivityDefType> _iterator = _activity.iterator();
        ActivityDefType _next = _iterator.next();
        String _id_2 = _next.getId();
        String _beatifyString_2 = this.beatifyString(_id_2);
        _builder.append(_beatifyString_2, "");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      int _priority = entry.getPriority();
      boolean _greaterThan = (_priority > 0);
      if (_greaterThan) {
        _builder.append("p ");
        String _id_3 = entry.getId();
        String _beatifyString_3 = this.beatifyString(_id_3);
        _builder.append(_beatifyString_3, "");
        _builder.append(" ");
        int _priority_1 = entry.getPriority();
        _builder.append(_priority_1, "");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      boolean _and = false;
      EList<EntryMakingCallType> _forwarding = entry.getForwarding();
      boolean _notEquals_2 = (!Objects.equal(_forwarding, null));
      if (!_notEquals_2) {
        _and = false;
      } else {
        EList<EntryMakingCallType> _forwarding_1 = entry.getForwarding();
        Iterator<EntryMakingCallType> _iterator_1 = _forwarding_1.iterator();
        boolean _hasNext = _iterator_1.hasNext();
        _and = _hasNext;
      }
      if (_and) {
        _builder.append("F ");
        String _id_4 = entry.getId();
        String _beatifyString_4 = this.beatifyString(_id_4);
        _builder.append(_beatifyString_4, "");
        _builder.append(" ");
        EList<EntryMakingCallType> _forwarding_2 = entry.getForwarding();
        Iterator<EntryMakingCallType> _iterator_2 = _forwarding_2.iterator();
        EntryMakingCallType _next_1 = _iterator_2.next();
        String _id_5 = _next_1.getId();
        String _beatifyString_5 = this.beatifyString(_id_5);
        _builder.append(_beatifyString_5, "");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      PhaseActivities _entryPhaseActivities = entry.getEntryPhaseActivities();
      boolean _notEquals_3 = (!Objects.equal(_entryPhaseActivities, null));
      if (_notEquals_3) {
        _builder.append("s ");
        String _id_6 = entry.getId();
        String _beatifyString_6 = this.beatifyString(_id_6);
        _builder.append(_beatifyString_6, "");
        _builder.append(" ");
        PhaseActivities _entryPhaseActivities_1 = entry.getEntryPhaseActivities();
        String _processPhaseActivities = this.processPhaseActivities(_entryPhaseActivities_1);
        _builder.append(_processPhaseActivities, "");
        _builder.append(" -1");
        _builder.newLineIfNotEmpty();
        {
          PhaseActivities _entryPhaseActivities_2 = entry.getEntryPhaseActivities();
          EList<ActivityPhasesType> _activity_1 = _entryPhaseActivities_2.getActivity();
          for(final ActivityPhasesType a : _activity_1) {
            {
              EList<ActivityMakingCallType> _synchCall = a.getSynchCall();
              for(final ActivityMakingCallType c : _synchCall) {
                _builder.append("y ");
                String _id_7 = entry.getId();
                String _beatifyString_7 = this.beatifyString(_id_7);
                _builder.append(_beatifyString_7, "");
                _builder.append(" ");
                EntryType _dest = c.getDest();
                String _name = _dest.getName();
                String _get = this.entries.get(_name);
                String _beatifyString_8 = this.beatifyString(_get);
                _builder.append(_beatifyString_8, "");
                _builder.append(" ");
                BigInteger _phase = a.getPhase();
                String _processActivityMakingCallType = this.processActivityMakingCallType(c, _phase);
                _builder.append(_processActivityMakingCallType, "");
                _builder.append("-1");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence processActivities(final ActivityDefType activity) {
    StringConcatenation _builder = new StringConcatenation();
    {
      Object _hostDemandMean = activity.getHostDemandMean();
      boolean _notEquals = (!Objects.equal(_hostDemandMean, null));
      if (_notEquals) {
        _builder.append("s ");
        String _id = activity.getId();
        _builder.append(_id, "");
        _builder.append(" ");
        Object _hostDemandMean_1 = activity.getHostDemandMean();
        _builder.append(_hostDemandMean_1, "");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      Object _hostDemandCvsq = activity.getHostDemandCvsq();
      boolean _notEquals_1 = (!Objects.equal(_hostDemandCvsq, null));
      if (_notEquals_1) {
        _builder.append("c ");
        String _id_1 = activity.getId();
        _builder.append(_id_1, "");
        _builder.append(" ");
        Object _hostDemandCvsq_1 = activity.getHostDemandCvsq();
        _builder.append(_hostDemandCvsq_1, "");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      CallOrderType _callOrder = activity.getCallOrder();
      boolean _notEquals_2 = (!Objects.equal(_callOrder, null));
      if (_notEquals_2) {
      }
    }
    {
      EList<ActivityMakingCallType> _synchCall = activity.getSynchCall();
      for(final ActivityMakingCallType s : _synchCall) {
        _builder.append("y ");
        String _id_2 = activity.getId();
        _builder.append(_id_2, "");
        _builder.append(" ");
        EntryType _dest = s.getDest();
        String _id_3 = _dest.getId();
        _builder.append(_id_3, "");
        _builder.append(" ");
        Object _callsMean = s.getCallsMean();
        _builder.append(_callsMean, "");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      EList<ActivityMakingCallType> _asynchCall = activity.getAsynchCall();
      for(final ActivityMakingCallType s_1 : _asynchCall) {
        _builder.append("z ");
        String _id_4 = activity.getId();
        _builder.append(_id_4, "");
        _builder.append(" ");
        EntryType _dest_1 = s_1.getDest();
        String _id_5 = _dest_1.getId();
        _builder.append(_id_5, "");
        _builder.append(" ");
        Object _callsMean_1 = s_1.getCallsMean();
        _builder.append(_callsMean_1, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public String processPhaseActivities(final PhaseActivities pActivities) {
    String str = "";
    for (int i = 1; (i <= this.nophases); i++) {
      {
        boolean found = false;
        for (int j = 0; (j < ((Object[])Conversions.unwrapArray(pActivities.getActivity(), Object.class)).length); j++) {
          {
            EList<ActivityPhasesType> _activity = pActivities.getActivity();
            ActivityPhasesType a = _activity.get(j);
            boolean _and = false;
            BigInteger _phase = a.getPhase();
            int _intValue = _phase.intValue();
            boolean _equals = (_intValue == i);
            if (!_equals) {
              _and = false;
            } else {
              Object _hostDemandMean = a.getHostDemandMean();
              boolean _notEquals = (!Objects.equal(_hostDemandMean, null));
              _and = _notEquals;
            }
            if (_and) {
              String _str = str;
              Object _hostDemandMean_1 = a.getHostDemandMean();
              String _plus = (" " + _hostDemandMean_1);
              String _plus_1 = (_plus + " ");
              str = (_str + _plus_1);
              found = true;
            }
          }
        }
        if ((!found)) {
          String _str = str;
          str = (_str + " 0 ");
        }
      }
    }
    return str;
  }
  
  public String processActivityMakingCallType(final ActivityMakingCallType callType, final BigInteger activityPhase) {
    String str = "";
    for (int i = 1; (i <= this.nophases); i++) {
      boolean _and = false;
      int _intValue = activityPhase.intValue();
      boolean _equals = (_intValue == i);
      if (!_equals) {
        _and = false;
      } else {
        Object _callsMean = callType.getCallsMean();
        boolean _notEquals = (!Objects.equal(_callsMean, null));
        _and = _notEquals;
      }
      if (_and) {
        String _str = str;
        Object _callsMean_1 = callType.getCallsMean();
        String _plus = (" " + _callsMean_1);
        String _plus_1 = (_plus + " ");
        str = (_str + _plus_1);
      } else {
        String _str_1 = str;
        str = (_str_1 + " 0 ");
      }
    }
    return str;
  }
  
  public String beatifyString(final String str) {
    return str.replaceAll("-", "__");
  }
}
