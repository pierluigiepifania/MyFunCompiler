package Visitor;

import Node.*;
import Node.StatementOp.*;
import Node.ExpressionOp.*;

public interface Visitor {

   public Object visit (ProgramOp n) throws Exception;
   public Object visit (BodyOp n) throws Exception;
   public Object visit (FunOp n) throws Exception;
   public Object visit (IdInitOp n) throws Exception;
   public Object visit (ParDeclOp n) throws Exception;
   public Object visit (VarDeclOp n) throws Exception;
   public Object visit (MainOp n) throws Exception;
   public Object visit (Identifier n)throws Exception;
   public Object visit (BooleanEx n)throws Exception;
   public Object visit (IntegerEx n)throws Exception;
   public Object visit (RealEx n)throws Exception;
   public Object visit (StringEx n)throws Exception;
   public Object visit (MultiOp n)throws Exception;
   public Object visit (SingOp n)throws Exception;
   public Object visit (NullOp n)throws Exception;
   public Object visit (OutparOp n)throws Exception;
   public Object visit (ReadOp n)throws Exception;
   public Object visit (WriteOp n)throws Exception;
   public Object visit (AssignOp n)throws Exception;
   public Object visit (WhileOp n)throws Exception;
   public Object visit (IfStatOp n)throws Exception;
   public Object visit (SwitchOp n)throws Exception;
   public Object visit (ElseStatOp n)throws Exception;
   public Object visit (ReturnOp n)throws Exception;
   public Object visit (CallFunOp n)throws Exception;
   public Object visit (CallfunEx n) throws Exception;

}
