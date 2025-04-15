# sif-unverified-code-examples

Collection of examples for information flow security in the presence of unverified code.

### Leaking Objects to Unverified Code

The 5 cases of leaking objects (i.e. passing them to unverified code) based on Frederic Neckers Master thesis<sup>
1</sup>:

1. A reference to the object is **returned by a method that unverified code called**
2. A reference to the object is **assigned to the public field of an unverified object**
3. A reference to the object is **assigned to the public field of a leaked object**
4. A reference to the object is **passed as the argument to a method of an unverified object**
5. A reference to the object is **indirectly passed** by being in a public field of another object when this other
   object is itself passed to the unverified code

##### Other cases, how leaked references can be obtained (but no new objects are being passed to unverified code)

6. A reference to the object is **returned by an unverified method**
7. A reference to the object is **retrieved from a public field of an unverified object**

Examples for every case can be found [here](src/main/java/vercors/sif/unverifedcode/examples/necker/design/LeakSecretByCases.java)

### Ways to leak high values
#### Concealed Fields
We call a field of a class _concealed_ if an adversary cannot learn any information about this field of a leaked object. This is the case for a field ```f```, if ```f``` is private and any non-private methods of the class do not leak any information about ```f``` (i.e. ```ensures low(f)```).

_TODO: consider package private and protected._

&rarr; i feel like something like this also makes sense in the scenario of F. Neckers master thesis. How did he go about it?

#### Explicitly:
1. Assigning high values to _non-concealed fields_ of leaked objects.
2. Leaking objects that contain high values in _non-concealed fields_.
3. Passing high values as parameters to unverified methods.

&rarr; should look similar to the 5 cases of leaking objects



### SIF with Unverified Code
We would like to encode every ```assert perm(leakable( x )) > 0``` as ```assert perm(leakable( x )) > 0 && assert low(x)```. However this doesn't work, since ```return x``` is encoded as ```assert perm(leakable( x )) > 0; return x```. When proving SIF it is crucial for methods to be able to return high values, where ```low(x)``` would not hold for the return value ```x```.
We need to make changes to Necker's encoding s.t. either
1. ```perm(leakable( x )) > 0 \implies low(x)```, must not always hold
2. or we have to change the "meaning of leakable" and thus the return encoding (and possibly more). Possible return encoding:
   ```assert hidden(this) == 0 ==> perm(leakable( x )) > 0; return x```?

[1] Frederic Necker. Verification of object invariants in the presence of unverified
code. Master’s thesis, ETH Zurich, 2023.
