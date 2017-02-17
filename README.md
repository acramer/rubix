# rubix

Simulates a Rubix Cube
*3 Files*
- Face.java - holds square values and the orientation of the other faces relative itself.
			- Relys heavily on the control of the cube file to call the right moves.
- Cube.java - Controls all the Face moves.  Has one Face to which it views all other faces relative to.
- rubix.java- holds main method.