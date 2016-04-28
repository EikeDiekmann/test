package exceptions;

import utility.AusgabeTexte;
   
public class NotATriangleException extends Exception implements AusgabeTexte
{
        /**
         * 
         */
        private static final long serialVersionUID = 2L;

        public NotATriangleException()
        {
            super(TXT_NO_TRIANGLE);
        }

}
