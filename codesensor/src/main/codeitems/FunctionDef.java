package main.codeitems;

import java.util.Stack;

import org.antlr.v4.runtime.ParserRuleContext;

import antlr.CodeSensorParser.Function_defContext;
import antlr.CodeSensorParser.Parameter_declContext;
import antlr.CodeSensorParser.Parameter_decl_clauseContext;
import antlr.CodeSensorParser.Return_typeContext;

public class FunctionDef extends NamedCodeItem{

	public ParameterList parameterList;
	public ReturnType returnType = null;
	
	@Override
	public void create(ParserRuleContext ctx, Stack<CodeItem> itemStack)
	{	
		nodeTypeName = "FUNCTION_DEF";
		super.create(ctx, itemStack);
	}


	public void setReturnType(Return_typeContext ctx, Stack<CodeItem> itemStack)
	{
		returnType = new ReturnType();
		returnType.create(ctx, itemStack);
	}


	public void setParameterList(Parameter_decl_clauseContext ctx, Stack<CodeItem> itemStack)
	{
		parameterList = new ParameterList();
		parameterList.create(ctx, itemStack);
	}

	public void addParameter(Parameter_declContext ctx, Stack<CodeItem> itemStack)
	{
		parameterList.addParameter(ctx, itemStack);
		
	}

	public String getFunctionSignature()
	{
		String retval = name.codeStr;
		if(parameterList != null)
			retval += " (" + parameterList.codeStr + ")";
		else
			retval += " ()";
		return retval;
	}

	public void complete(Function_defContext ctx)
	{
		codeStr = getFunctionSignature();
	}
	
}