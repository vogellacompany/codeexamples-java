
package de.vogella.scala.uniformaccess

class Square {
        private  var l = 1 ;
        var wide = 1;

        def length   = l;

        def length_=(s : Int)= {
        	require (s > 0 )
        	this.l = s
    }
}
