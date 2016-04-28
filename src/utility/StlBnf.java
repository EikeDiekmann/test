package utility;

/**
 * StlBNF -STL Backus Naur Form
 * @author Eike
 *
 */
public interface StlBnf 
{

    //Terminal Symbole
    public static final String TERMINAL_SOLID = "SOLID";
    public static final String TERMINAL_ENDSOLID = "ENDSOLID";
    public static final String TERMINAL_NORMAL = "NORMAL";
    public static final String TERMINAL_OUTER_LOOP = "OUTER LOOP";
    public static final String TERMINAL_OUTER = "OUTER";
    public static final String TERMINAL_LOOP = "LOOP";
    public static final String TERMINAL_ENDLOOP ="ENDLOOP";
    public static final String TERMINAL_FACET = "FACET";
    public static final String TERMINAL_ENDFACET = "ENDFACET";
    public static final String TERMINAL_VERTEX = "VERTEX";
    

    //Grammatik
    public static final String GRMA_DOUBLE = "[-+]?[0-9]*((\\.)|(\\,))?[0-9]+([eE][-+]?[0-9]+)?";     //beliebig viele Ziffern, dann ein Punkt "." oder Komma "," gefolgt von Ziffern
    public static final String GRMA_VERTEX = TERMINAL_VERTEX + "(\\s)?+" + "(" + GRMA_DOUBLE + "(\\s)?+" + ")"  + "{3,}+";  //"VERTEX" gefolgt von 3mal <GRMA_GLEITKOMMAZAHL>
    public static final String GRMA_LOOP =  TERMINAL_OUTER + "(\\s)?+" + TERMINAL_LOOP + "(" + "(\\s)?+"  + GRMA_VERTEX  + "(\\s)?+" + ")" +"{3,}+" +  "(\\s)?"  + TERMINAL_ENDLOOP ;  //"OUTER LOOP", 3 mal <GRMA_VERTEX> gefolgt von "ENDLOOP"  
    public static final String GRMA_NORMAL = TERMINAL_NORMAL + "(\\s)?" + "(" + GRMA_DOUBLE + "(\\s)?+" + ")"  + "{3,}+";   //"NORMAL" + 3mal <GRMA_GLEITKOMMAZAHL> 
    public static final String GRMA_FACET = TERMINAL_FACET + "(\\s)?+" + GRMA_NORMAL + "(\\s)?+" + GRMA_LOOP + "(\\s)?+" +  TERMINAL_ENDFACET + "(\\s)?+" ;   //"FACET" <GRMA_NORMAL> <GRMA_LOOP> "ENDFACET"
    public static final String GRMA_NAME = "[A-Za-z_][A-Za-z0-9_]*" ;   //
    public static final String GRMA_STL = TERMINAL_SOLID + "(\\s)?+" + GRMA_NAME + "(\\s)?+" + "(" +"(\\s)?+"+ GRMA_FACET + "(\\s)?+" + ")" + "{1,}+" + TERMINAL_ENDSOLID + "(\\s)?+" + GRMA_NAME;  //"SOLID" <GRMA_NAME> mindestens 1mal <GRMA_FACET> "ENDSOLID"
       
}
