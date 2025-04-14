# sif-unverified-code-examples

Collection of examples for information flow security in the presence of unverified code.

### Leaking Objects to Unverified Code

The 6 cases of leaking objects (i.e. passing them to unverified code) based on Frederic Neckers Master thesis<sup>
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

[1] Frederic Necker. Verification of object invariants in the presence of unverified
code. Master’s thesis, ETH Zurich, 2023.
