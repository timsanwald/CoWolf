package de.uni_stuttgart.iste.cowolf.analyze.lqn.lqnsolver

import de.uni_stuttgart.iste.cowolf.model.LqnCore.ActivityDefType
import de.uni_stuttgart.iste.cowolf.model.LqnCore.ActivityMakingCallType
import de.uni_stuttgart.iste.cowolf.model.LqnCore.EntryType
import de.uni_stuttgart.iste.cowolf.model.LqnCore.LQN
import de.uni_stuttgart.iste.cowolf.model.LqnCore.LqnModelType
import de.uni_stuttgart.iste.cowolf.model.LqnCore.PhaseActivities
import de.uni_stuttgart.iste.cowolf.model.LqnCore.ProcessorType
import de.uni_stuttgart.iste.cowolf.model.LqnCore.SolverParamsType
import de.uni_stuttgart.iste.cowolf.model.LqnCore.TaskType
import java.math.BigInteger
import java.util.HashMap
import java.util.Map

class LQNSolverFileStandardGenerator implements LQNSolverFileGenerator {
	
	private val processorSchedules = #{'fcfs'->'f', 'ps'->'s', 'pp'->'p', 'rand'->'r', 'hol'->'h', 'ps-hol'->'h','ps-pp'->'s'};
	private val taskSchedules = #{'ref'->'r', 'fcfs'->'f', 'pri'->'', 'hol'->'h', 'burst'->'b','poll'->'p','inf'->'i','semaphore'->'S'};
	private val nophases = 3;
	
	private Map<String,String> entries;
	
	
	override doGenerateLQNSolverFile(LQN root, Map<String, Object> parameters) {
		init(root);
		return '''«lqnStandardTemplate(root.lqnModel, parameters)»'''
	}
	
	def init(LQN root){
		entries = new HashMap<String,String>();
		for(EntryType e : root.eAllContents.filter(EntryType).toIterable){
			if(e.name != null && e.id != null){
				entries.put(e.name,e.id);
			}
		}
	}
	
	def lqnStandardTemplate(LqnModelType r, Map<String, Object> parameters)'''
		«processSolverParams(r.solverParams)»
		
		P 0
		«FOR p : r.processor»
		«p.processProcessor»
		«ENDFOR»
		-1
		
		T 0
		«FOR t : r.eAllContents.filter(TaskType).toList»
		«t.processTask»
		«ENDFOR»		
		-1
		
		E 0
		«FOR e : r.eAllContents.filter(EntryType).toList»
		«e.processEntry»
		«ENDFOR»
		-1
		
		«FOR t : r.eAllContents.filter(TaskType).toList»
		«FOR a : t.eAllContents.filter(ActivityDefType).toList»
		A «t.id»
		«a.processActivities»
		-1
		
		«ENDFOR»
		«ENDFOR»
	'''
	
	def processSolverParams(SolverParamsType solverParams)
	'''G "LQN Model. Generated by CoWolf"
«solverParams.convVal»
«solverParams.itLimit»
«solverParams.printInt»
«solverParams.underrelaxCoeff»
-1'''
	
	def processProcessor(ProcessorType processor)'''
		p «processor.id.beatifyString» «processorSchedules.get(processor.scheduling.getName())» «IF processor.multiplicity.intValue > 0»m «processor.multiplicity»«ENDIF» «IF processor.replication.intValue > 0»r «processor.replication»«ENDIF»
	'''
	
	def processTask(TaskType task)'''
		t «task.id.beatifyString» «taskSchedules.get(task.scheduling.getName())» «FOR e : task.entry SEPARATOR ' '»«e.id.beatifyString»«ENDFOR» -1 «ProcessorType.cast(task.eContainer).id.beatifyString»«IF task.queueLength.intValue > 0»«task.queueLength»«ENDIF»   «IF task.priority.intValue > 0»«task.priority»«ENDIF» «IF task.thinkTime != 0 »«task.thinkTime»«ENDIF» «IF task.multiplicity.intValue > 0»m «task.multiplicity»«ENDIF» «IF task.replication.intValue > 0»r «task.replication»«ENDIF»
	'''
	
	def processEntry(EntryType entry)'''
		«IF entry.openArrivalRate != null»
		a «entry.id.beatifyString» «entry.openArrivalRate»
		«ENDIF»
		«IF entry.entryActivityGraph != null»
		A «entry.id.beatifyString» «entry.entryActivityGraph.activity.iterator.next.id.beatifyString»
		«ENDIF»
		«IF entry.priority > 0»
		p «entry.id.beatifyString» «entry.priority»
		«ENDIF»
		«IF entry.forwarding != null && entry.forwarding.iterator.hasNext»
		F «entry.id.beatifyString» «entry.forwarding.iterator.next.id.beatifyString»
		«ENDIF»
		«IF entry.entryPhaseActivities != null»
		s «entry.id.beatifyString» «processPhaseActivities(entry.entryPhaseActivities)» -1
		«FOR a : entry.entryPhaseActivities.activity»
		«FOR c : a.synchCall»
		y «entry.id.beatifyString» «entries.get(c.dest.name).beatifyString» «processActivityMakingCallType(c,a.phase)»-1
		«ENDFOR»
		«ENDFOR»
		«ENDIF»
	'''
	
	def processActivities(ActivityDefType activity)'''
		«IF activity.hostDemandMean != null»
		s «activity.id» «activity.hostDemandMean»
		«ENDIF»
		«IF activity.hostDemandCvsq != null»
		c «activity.id» «activity.hostDemandCvsq»
		«ENDIF»
		«IF activity.callOrder != null»
«««		Solver not working properly with this
«««		f «activity.id» «activity.callOrder.value»
		«ENDIF»
		«FOR s : activity.synchCall»
		y «activity.id» «s.dest.id» «s.callsMean»
		«ENDFOR»
		«FOR s : activity.asynchCall»
		z «activity.id» «s.dest.id» «s.callsMean»
		«ENDFOR»
	'''
	
	def processPhaseActivities(PhaseActivities pActivities){
		var str = "";
		for(var i=1; i<=nophases; i++){
			var found = false;
			for(var j=0; j<pActivities.activity.length; j++){
				var a = pActivities.activity.get(j);
				if (a.phase.intValue == i && a.hostDemandMean != null){
					str += ' '+a.hostDemandMean+' ';
					found = true;
				}
			}
			if(!found){
				str += ' 0 ';
			}
		}
		return str;
	}
	
	def processActivityMakingCallType(ActivityMakingCallType callType, BigInteger activityPhase){
		var str = "";
		for(var i=1; i<=nophases; i++){
			if (activityPhase.intValue == i && callType.callsMean != null){
				str += ' '+callType.callsMean+' ';
			}
			else{
				str += ' 0 ';
			}
		}
		return str;
	}
	
	def beatifyString(String str){
		return str.replaceAll("-","__");
	}
}