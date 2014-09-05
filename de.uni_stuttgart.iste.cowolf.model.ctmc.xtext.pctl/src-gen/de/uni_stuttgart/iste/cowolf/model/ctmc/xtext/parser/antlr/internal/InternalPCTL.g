/*
* generated by Xtext
*/
grammar InternalPCTL;

options {
	superClass=AbstractInternalAntlrParser;
	
}

@lexer::header {
package de.uni_stuttgart.iste.cowolf.model.ctmc.xtext.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

@parser::header {
package de.uni_stuttgart.iste.cowolf.model.ctmc.xtext.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import de.uni_stuttgart.iste.cowolf.model.ctmc.xtext.services.PCTLGrammarAccess;

}

@parser::members {

 	private PCTLGrammarAccess grammarAccess;
 	
    public InternalPCTLParser(TokenStream input, PCTLGrammarAccess grammarAccess) {
        this(input);
        this.grammarAccess = grammarAccess;
        registerRules(grammarAccess.getGrammar());
    }
    
    @Override
    protected String getFirstRuleName() {
    	return "Start";	
   	}
   	
   	@Override
   	protected PCTLGrammarAccess getGrammarAccess() {
   		return grammarAccess;
   	}
}

@rulecatch { 
    catch (RecognitionException re) { 
        recover(input,re); 
        appendSkippedTokens();
    } 
}




// Entry rule entryRuleStart
entryRuleStart returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getStartRule()); }
	 iv_ruleStart=ruleStart 
	 { $current=$iv_ruleStart.current; } 
	 EOF 
;

// Rule Start
ruleStart returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
(
		{ 
	        newCompositeNode(grammarAccess.getStartAccess().getStartSParserRuleCall_0_0()); 
	    }
		lv_start_0_0=ruleS		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getStartRule());
	        }
       		set(
       			$current, 
       			"start",
        		lv_start_0_0, 
        		"S");
	        afterParserOrEnumRuleCall();
	    }

)
)
    |
    { 
        newCompositeNode(grammarAccess.getStartAccess().getPParserRuleCall_1()); 
    }
    this_P_1=ruleP
    { 
        $current = $this_P_1.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getStartAccess().getPGParserRuleCall_2()); 
    }
    this_PG_2=rulePG
    { 
        $current = $this_PG_2.current; 
        afterParserOrEnumRuleCall();
    }
)
;





// Entry rule entryRuleState
entryRuleState returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getStateRule()); }
	 iv_ruleState=ruleState 
	 { $current=$iv_ruleState.current; } 
	 EOF 
;

// Rule State
ruleState returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((	otherlv_0='(' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getStateAccess().getLeftParenthesisKeyword_0_0());
    }
	otherlv_1='State:' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getStateAccess().getStateKeyword_0_1());
    }
(
(
		lv_name_2_0=RULE_ID
		{
			newLeafNode(lv_name_2_0, grammarAccess.getStateAccess().getNameIDTerminalRuleCall_0_2_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getStateRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_2_0, 
        		"ID");
	    }

)
)	otherlv_3=')' 
    {
    	newLeafNode(otherlv_3, grammarAccess.getStateAccess().getRightParenthesisKeyword_0_3());
    }
)
    |(	otherlv_4='State:' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getStateAccess().getStateKeyword_1_0());
    }
(
(
		lv_name_5_0=RULE_ID
		{
			newLeafNode(lv_name_5_0, grammarAccess.getStateAccess().getNameIDTerminalRuleCall_1_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getStateRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_5_0, 
        		"ID");
	    }

)
)))
;





// Entry rule entryRuleLabel
entryRuleLabel returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getLabelRule()); }
	 iv_ruleLabel=ruleLabel 
	 { $current=$iv_ruleLabel.current; } 
	 EOF 
;

// Rule Label
ruleLabel returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((	otherlv_0='(' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getLabelAccess().getLeftParenthesisKeyword_0_0());
    }
	otherlv_1='Label:' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getLabelAccess().getLabelKeyword_0_1());
    }
(
(
		lv_name_2_0=RULE_ID
		{
			newLeafNode(lv_name_2_0, grammarAccess.getLabelAccess().getNameIDTerminalRuleCall_0_2_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getLabelRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_2_0, 
        		"ID");
	    }

)
)	otherlv_3=')' 
    {
    	newLeafNode(otherlv_3, grammarAccess.getLabelAccess().getRightParenthesisKeyword_0_3());
    }
)
    |(	otherlv_4='Label:' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getLabelAccess().getLabelKeyword_1_0());
    }
(
(
		lv_name_5_0=RULE_ID
		{
			newLeafNode(lv_name_5_0, grammarAccess.getLabelAccess().getNameIDTerminalRuleCall_1_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getLabelRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_5_0, 
        		"ID");
	    }

)
)))
;





// Entry rule entryRuleStateOrLabel
entryRuleStateOrLabel returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getStateOrLabelRule()); }
	 iv_ruleStateOrLabel=ruleStateOrLabel 
	 { $current=$iv_ruleStateOrLabel.current; } 
	 EOF 
;

// Rule StateOrLabel
ruleStateOrLabel returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getStateOrLabelAccess().getStateParserRuleCall_0()); 
    }
    this_State_0=ruleState
    { 
        $current = $this_State_0.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getStateOrLabelAccess().getLabelParserRuleCall_1()); 
    }
    this_Label_1=ruleLabel
    { 
        $current = $this_Label_1.current; 
        afterParserOrEnumRuleCall();
    }
)
;





// Entry rule entryRuleS
entryRuleS returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getSRule()); }
	 iv_ruleS=ruleS 
	 { $current=$iv_ruleS.current; } 
	 EOF 
;

// Rule S
ruleS returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='S' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getSAccess().getSKeyword_0());
    }

    { 
        newCompositeNode(grammarAccess.getSAccess().getCompareParserRuleCall_1()); 
    }
ruleCompare
    { 
        afterParserOrEnumRuleCall();
    }
	otherlv_2='[' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getSAccess().getLeftSquareBracketKeyword_2());
    }

    { 
        newCompositeNode(grammarAccess.getSAccess().getStateOrLabelParserRuleCall_3()); 
    }
    this_StateOrLabel_3=ruleStateOrLabel
    { 
        $current = $this_StateOrLabel_3.current; 
        afterParserOrEnumRuleCall();
    }
	otherlv_4=']' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getSAccess().getRightSquareBracketKeyword_4());
    }
)
;





// Entry rule entryRuleP
entryRuleP returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getPRule()); }
	 iv_ruleP=ruleP 
	 { $current=$iv_ruleP.current; } 
	 EOF 
;

// Rule P
ruleP returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((	otherlv_0='P' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getPAccess().getPKeyword_0_0());
    }

    { 
        newCompositeNode(grammarAccess.getPAccess().getCompareParserRuleCall_0_1()); 
    }
ruleCompare
    { 
        afterParserOrEnumRuleCall();
    }
	otherlv_2='[' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getPAccess().getLeftSquareBracketKeyword_0_2());
    }
	otherlv_3='F' 
    {
    	newLeafNode(otherlv_3, grammarAccess.getPAccess().getFKeyword_0_3());
    }

    { 
        newCompositeNode(grammarAccess.getPAccess().getTimeBoundParserRuleCall_0_4()); 
    }
ruleTimeBound
    { 
        afterParserOrEnumRuleCall();
    }

    { 
        newCompositeNode(grammarAccess.getPAccess().getStateOrLabelParserRuleCall_0_5()); 
    }
    this_StateOrLabel_5=ruleStateOrLabel
    { 
        $current = $this_StateOrLabel_5.current; 
        afterParserOrEnumRuleCall();
    }
	otherlv_6=']' 
    {
    	newLeafNode(otherlv_6, grammarAccess.getPAccess().getRightSquareBracketKeyword_0_6());
    }
)
    |(	otherlv_7='P' 
    {
    	newLeafNode(otherlv_7, grammarAccess.getPAccess().getPKeyword_1_0());
    }

    { 
        newCompositeNode(grammarAccess.getPAccess().getCompareParserRuleCall_1_1()); 
    }
ruleCompare
    { 
        afterParserOrEnumRuleCall();
    }
	otherlv_9='[' 
    {
    	newLeafNode(otherlv_9, grammarAccess.getPAccess().getLeftSquareBracketKeyword_1_2());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getPAccess().getLEFTStateOrLabelParserRuleCall_1_3_0()); 
	    }
		lv_LEFT_10_0=ruleStateOrLabel		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getPRule());
	        }
       		set(
       			$current, 
       			"LEFT",
        		lv_LEFT_10_0, 
        		"StateOrLabel");
	        afterParserOrEnumRuleCall();
	    }

)
)	otherlv_11='U' 
    {
    	newLeafNode(otherlv_11, grammarAccess.getPAccess().getUKeyword_1_4());
    }

    { 
        newCompositeNode(grammarAccess.getPAccess().getTimeBoundParserRuleCall_1_5()); 
    }
ruleTimeBound
    { 
        afterParserOrEnumRuleCall();
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getPAccess().getRIGHTStateOrLabelParserRuleCall_1_6_0()); 
	    }
		lv_RIGHT_13_0=ruleStateOrLabel		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getPRule());
	        }
       		set(
       			$current, 
       			"RIGHT",
        		lv_RIGHT_13_0, 
        		"StateOrLabel");
	        afterParserOrEnumRuleCall();
	    }

)
)	otherlv_14=']' 
    {
    	newLeafNode(otherlv_14, grammarAccess.getPAccess().getRightSquareBracketKeyword_1_7());
    }
))
;





// Entry rule entryRulePG
entryRulePG returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getPGRule()); }
	 iv_rulePG=rulePG 
	 { $current=$iv_rulePG.current; } 
	 EOF 
;

// Rule PG
rulePG returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='P' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getPGAccess().getPKeyword_0());
    }

    { 
        newCompositeNode(grammarAccess.getPGAccess().getCompareParserRuleCall_1()); 
    }
ruleCompare
    { 
        afterParserOrEnumRuleCall();
    }
	otherlv_2='[' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getPGAccess().getLeftSquareBracketKeyword_2());
    }
	otherlv_3='G' 
    {
    	newLeafNode(otherlv_3, grammarAccess.getPGAccess().getGKeyword_3());
    }
	otherlv_4='(' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getPGAccess().getLeftParenthesisKeyword_4());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getPGAccess().getLEFTStateOrLabelParserRuleCall_5_0()); 
	    }
		lv_LEFT_5_0=ruleStateOrLabel		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getPGRule());
	        }
       		set(
       			$current, 
       			"LEFT",
        		lv_LEFT_5_0, 
        		"StateOrLabel");
	        afterParserOrEnumRuleCall();
	    }

)
)	otherlv_6='=>' 
    {
    	newLeafNode(otherlv_6, grammarAccess.getPGAccess().getEqualsSignGreaterThanSignKeyword_6());
    }
	otherlv_7='P' 
    {
    	newLeafNode(otherlv_7, grammarAccess.getPGAccess().getPKeyword_7());
    }

    { 
        newCompositeNode(grammarAccess.getPGAccess().getCompareWithoutUnknownParserRuleCall_8()); 
    }
ruleCompareWithoutUnknown
    { 
        afterParserOrEnumRuleCall();
    }
	otherlv_9='[' 
    {
    	newLeafNode(otherlv_9, grammarAccess.getPGAccess().getLeftSquareBracketKeyword_9());
    }
	otherlv_10='F' 
    {
    	newLeafNode(otherlv_10, grammarAccess.getPGAccess().getFKeyword_10());
    }

    { 
        newCompositeNode(grammarAccess.getPGAccess().getTimeBoundParserRuleCall_11()); 
    }
ruleTimeBound
    { 
        afterParserOrEnumRuleCall();
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getPGAccess().getRIGHTStateOrLabelParserRuleCall_12_0()); 
	    }
		lv_RIGHT_12_0=ruleStateOrLabel		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getPGRule());
	        }
       		set(
       			$current, 
       			"RIGHT",
        		lv_RIGHT_12_0, 
        		"StateOrLabel");
	        afterParserOrEnumRuleCall();
	    }

)
)	otherlv_13=']' 
    {
    	newLeafNode(otherlv_13, grammarAccess.getPGAccess().getRightSquareBracketKeyword_13());
    }
	otherlv_14=')' 
    {
    	newLeafNode(otherlv_14, grammarAccess.getPGAccess().getRightParenthesisKeyword_14());
    }
	otherlv_15=']' 
    {
    	newLeafNode(otherlv_15, grammarAccess.getPGAccess().getRightSquareBracketKeyword_15());
    }
)
;





// Entry rule entryRuleCompare
entryRuleCompare returns [String current=null] 
	:
	{ newCompositeNode(grammarAccess.getCompareRule()); } 
	 iv_ruleCompare=ruleCompare 
	 { $current=$iv_ruleCompare.current.getText(); }  
	 EOF 
;

// Rule Compare
ruleCompare returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getCompareAccess().getCompareWithoutUnknownParserRuleCall_0()); 
    }
    this_CompareWithoutUnknown_0=ruleCompareWithoutUnknown    {
		$current.merge(this_CompareWithoutUnknown_0);
    }

    { 
        afterParserOrEnumRuleCall();
    }

    |
	kw='=?' 
    {
        $current.merge(kw);
        newLeafNode(kw, grammarAccess.getCompareAccess().getEqualsSignQuestionMarkKeyword_1()); 
    }
)
    ;





// Entry rule entryRuleCompareWithoutUnknown
entryRuleCompareWithoutUnknown returns [String current=null] 
	:
	{ newCompositeNode(grammarAccess.getCompareWithoutUnknownRule()); } 
	 iv_ruleCompareWithoutUnknown=ruleCompareWithoutUnknown 
	 { $current=$iv_ruleCompareWithoutUnknown.current.getText(); }  
	 EOF 
;

// Rule CompareWithoutUnknown
ruleCompareWithoutUnknown returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
	kw='>' 
    {
        $current.merge(kw);
        newLeafNode(kw, grammarAccess.getCompareWithoutUnknownAccess().getGreaterThanSignKeyword_0_0()); 
    }

    { 
        newCompositeNode(grammarAccess.getCompareWithoutUnknownAccess().getDECIMALParserRuleCall_0_1()); 
    }
    this_DECIMAL_1=ruleDECIMAL    {
		$current.merge(this_DECIMAL_1);
    }

    { 
        afterParserOrEnumRuleCall();
    }
)
    |(
	kw='<' 
    {
        $current.merge(kw);
        newLeafNode(kw, grammarAccess.getCompareWithoutUnknownAccess().getLessThanSignKeyword_1_0()); 
    }

    { 
        newCompositeNode(grammarAccess.getCompareWithoutUnknownAccess().getDECIMALParserRuleCall_1_1()); 
    }
    this_DECIMAL_3=ruleDECIMAL    {
		$current.merge(this_DECIMAL_3);
    }

    { 
        afterParserOrEnumRuleCall();
    }
)
    |(
	kw='>=' 
    {
        $current.merge(kw);
        newLeafNode(kw, grammarAccess.getCompareWithoutUnknownAccess().getGreaterThanSignEqualsSignKeyword_2_0()); 
    }

    { 
        newCompositeNode(grammarAccess.getCompareWithoutUnknownAccess().getDECIMALParserRuleCall_2_1()); 
    }
    this_DECIMAL_5=ruleDECIMAL    {
		$current.merge(this_DECIMAL_5);
    }

    { 
        afterParserOrEnumRuleCall();
    }
)
    |(
	kw='<=' 
    {
        $current.merge(kw);
        newLeafNode(kw, grammarAccess.getCompareWithoutUnknownAccess().getLessThanSignEqualsSignKeyword_3_0()); 
    }

    { 
        newCompositeNode(grammarAccess.getCompareWithoutUnknownAccess().getDECIMALParserRuleCall_3_1()); 
    }
    this_DECIMAL_7=ruleDECIMAL    {
		$current.merge(this_DECIMAL_7);
    }

    { 
        afterParserOrEnumRuleCall();
    }
))
    ;





// Entry rule entryRuleTimeBound
entryRuleTimeBound returns [String current=null] 
	:
	{ newCompositeNode(grammarAccess.getTimeBoundRule()); } 
	 iv_ruleTimeBound=ruleTimeBound 
	 { $current=$iv_ruleTimeBound.current.getText(); }  
	 EOF 
;

// Rule TimeBound
ruleTimeBound returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
	kw='>=' 
    {
        $current.merge(kw);
        newLeafNode(kw, grammarAccess.getTimeBoundAccess().getGreaterThanSignEqualsSignKeyword_0_0()); 
    }

    { 
        newCompositeNode(grammarAccess.getTimeBoundAccess().getDECIMALParserRuleCall_0_1()); 
    }
    this_DECIMAL_1=ruleDECIMAL    {
		$current.merge(this_DECIMAL_1);
    }

    { 
        afterParserOrEnumRuleCall();
    }
)
    |(
	kw='<=' 
    {
        $current.merge(kw);
        newLeafNode(kw, grammarAccess.getTimeBoundAccess().getLessThanSignEqualsSignKeyword_1_0()); 
    }

    { 
        newCompositeNode(grammarAccess.getTimeBoundAccess().getDECIMALParserRuleCall_1_1()); 
    }
    this_DECIMAL_3=ruleDECIMAL    {
		$current.merge(this_DECIMAL_3);
    }

    { 
        afterParserOrEnumRuleCall();
    }
)
    |(
	kw='[' 
    {
        $current.merge(kw);
        newLeafNode(kw, grammarAccess.getTimeBoundAccess().getLeftSquareBracketKeyword_2_0()); 
    }

    { 
        newCompositeNode(grammarAccess.getTimeBoundAccess().getDECIMALParserRuleCall_2_1()); 
    }
    this_DECIMAL_5=ruleDECIMAL    {
		$current.merge(this_DECIMAL_5);
    }

    { 
        afterParserOrEnumRuleCall();
    }

	kw=',' 
    {
        $current.merge(kw);
        newLeafNode(kw, grammarAccess.getTimeBoundAccess().getCommaKeyword_2_2()); 
    }

    { 
        newCompositeNode(grammarAccess.getTimeBoundAccess().getDECIMALParserRuleCall_2_3()); 
    }
    this_DECIMAL_7=ruleDECIMAL    {
		$current.merge(this_DECIMAL_7);
    }

    { 
        afterParserOrEnumRuleCall();
    }

	kw=']' 
    {
        $current.merge(kw);
        newLeafNode(kw, grammarAccess.getTimeBoundAccess().getRightSquareBracketKeyword_2_4()); 
    }
))?
    ;





// Entry rule entryRuleDECIMAL
entryRuleDECIMAL returns [String current=null] 
	:
	{ newCompositeNode(grammarAccess.getDECIMALRule()); } 
	 iv_ruleDECIMAL=ruleDECIMAL 
	 { $current=$iv_ruleDECIMAL.current.getText(); }  
	 EOF 
;

// Rule DECIMAL
ruleDECIMAL returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(    this_INT_0=RULE_INT    {
		$current.merge(this_INT_0);
    }

    { 
    newLeafNode(this_INT_0, grammarAccess.getDECIMALAccess().getINTTerminalRuleCall_0()); 
    }
(
	kw='.' 
    {
        $current.merge(kw);
        newLeafNode(kw, grammarAccess.getDECIMALAccess().getFullStopKeyword_1_0()); 
    }
    this_INT_2=RULE_INT    {
		$current.merge(this_INT_2);
    }

    { 
    newLeafNode(this_INT_2, grammarAccess.getDECIMALAccess().getINTTerminalRuleCall_1_1()); 
    }
)?)
    ;





RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;


