import java_cup.runtime.Symbol;
import SymbolTable.*;

%%

%class Lexer
%unicode
%cupsym Token
%cup
%line
%column

%{
	StringBuffer string = new StringBuffer();

	private Symbol symbol(int type)
	{
		return new Symbol(type);
	}

	private Symbol symbol(int type, String value)
	{
		Symbol toReturn;
		toReturn = new Symbol(type, yyline, yycolumn, value);
		return toReturn;
	}

      private void error(String message) {
        System.out.println("Line:"+(yyline+1)+" Column:"+(yycolumn+1)+message);
      }
%}

digit = [0-9]
id = [:jletter:] [:jletterdigit:]*
int_const = {digit}+
double_const = {int_const} ("." {int_const})? ("E" ("+-")? {int_const})?
whitespace = [\r|\n|\r\n]+ | [ \t\f]
string = [^\"]*

semi = ";"
comma = ","
lpar = "("
rpar = ")"
colon = ":"
read = "%"
write = "?"
writeln = "?."
writeb = "?,"
writet = "?:"
plus = "+"
minus = "-"
times = "*"
div = "/"
assign = ":="
gt = ">"
ge = ">="
lt = "<"
le = "<="
eq = "="
pow = "^"
ne = "!="
outpar = "@"
str_concat = "&"

%%

<YYINITIAL> {

	/* keywords */
	"main" { return symbol(Token.MAIN, yytext()); }
	"integer" { return symbol(Token.INTEGER, yytext()); }
	"string" { return symbol(Token.STRING, yytext()); }
	"bool" { return symbol(Token.BOOL, yytext()); }
	"real" { return symbol(Token.REAL, yytext()); }
	"null" { return symbol(Token.NULL, yytext()); }
	"fun" { return symbol(Token.FUN, yytext()); }
	"if" { return symbol(Token.IF, yytext()); }
	"then" { return symbol(Token.THEN, yytext()); }
	"else" { return symbol(Token.ELSE, yytext()); }
	"while" { return symbol(Token.WHILE, yytext()); }
	"end" { return symbol(Token.END, yytext()); }
	"true" { return symbol(Token.TRUE, yytext()); }
	"false" { return symbol(Token.FALSE, yytext()); }
	"not" { return symbol(Token.NOT, yytext()); }
	"loop" { return symbol(Token.LOOP, yytext()); }
	"div_int" { return symbol(Token.DIVINT, yytext()); }
	"true" { return symbol(Token.TRUE, yytext()); }
	"and" { return symbol(Token.AND, yytext()); }
	"or" { return symbol(Token.OR, yytext()); }
	"return" { return symbol(Token.RETURN, yytext()); }
	"end" { return symbol(Token.END, yytext()); }
	"var" { return symbol(Token.VAR, yytext()); }
	"out" { return symbol(Token.OUT, yytext()); }

	/* literals */
	{int_const} { return symbol(Token.NUMBER_INT, yytext()); }
	{double_const} { return symbol(Token.NUMBER_REAL, yytext()); }
	\"{string}\" {return symbol(Token.STRING_CONST, yytext());}
	\"{string} {error("stringa non chiusa");}
	[']([^'\\\n]|\\(.|\n))*['] {return symbol(Token.STRING_CONST, yytext());}
    "#*" [^*] ~"#" | "#" "*"+ "#" {}
    "#*" {error ("commento non chiuso");}

	/* separators */
	{semi} { return symbol(Token.SEMI, yytext()); }
	{comma} { return symbol(Token.COMMA, yytext()); }

	/* operators */
	{lpar} { return symbol(Token.LPAR, yytext()); }
	{rpar} { return symbol(Token.RPAR, yytext()); }
	{colon} { return symbol(Token.COLON, yytext()); }
	{plus} { return symbol(Token.PLUS, yytext()); }
	{minus} { return symbol(Token.MINUS, yytext()); }
	{times} { return symbol(Token.TIMES, yytext()); }
	{div} { return symbol(Token.DIV, yytext()); }
	{gt} { return symbol(Token.GT, yytext()); }
	{ge} { return symbol(Token.GE, yytext()); }
	{lt} { return symbol(Token.LT, yytext()); }
	{le} { return symbol(Token.LE, yytext()); }
	{eq} { return symbol(Token.EQ, yytext()); }
	{pow} { return symbol(Token.POW, yytext()); }
	{ne} { return symbol(Token.NE, yytext()); }
	{outpar} { return symbol(Token.OUTPAR, yytext()); }
	{str_concat} { return symbol(Token.STR_CONCAT, yytext()); }

	/* assignment operators */
	{assign} { return symbol(Token.ASSIGN, yytext()); }

	/* identifiers */
	{id} { return symbol(Token.ID, yytext()); }

	/* whitespace */
	{whitespace} { /* ignore */ }

	/* read and write */
	{read} { return symbol(Token.READ, yytext()); }
	{write} { return symbol(Token.WRITE, yytext()); }
	{writeln} { return symbol(Token.WRITELN, yytext()); }
	{writeb} { return symbol(Token.WRITEB, yytext()); }
	{writet} { return symbol(Token.WRITET, yytext()); }

}

[^]		{ throw new Error("Illegal character <"+yytext()+"> at line "+yyline+", column "+yycolumn);
		}

<<EOF>>	{ return symbol(Token.EOF); }


