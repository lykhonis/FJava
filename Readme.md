##DESCRIPTION:
F(unctional)Java translator by Vladimir Lichonos.

- Simple tool should run before Java compiler to translate some structures of the language to 'clean' Java code.
- All arguments passes to this tool has to be *.fjava files, that can even have no FJava structures. Tool will create .java file with same name in same package (folder) so newly created java file can be compiled by Java compiler.
- Be able to use this tool it's required to add com.fjava.library to classpath to your project. There are no files to be attached to your code, except if you use, for example, () => {} structure, tool will replace it with some interface F<...>(...) so this file will be compiled by Java compiler and attached to your project. If you don't use any structured, additional files won't be attached at all.
  
##USAGE:
v0.1:

- Simple class declaration (holders of some values):
	- Usage:
	
			[...] class <Identifier>([[<public> | <protected> | <private>] <Type> <Identifier> [, ...]]*)
			
	- Result in Java:
	
			[...] class <Identifier> {
			
				([<public> | <protected> | <private>] | <public>) final <Type> <Identifier>;
				[...]*
				
				public <Identifier>([<Type> <Identifier> [, ...]]*) {
					this.[Identifier] = [Identifier];
					[...]*
				}
			}
			
	- Example:
	
			class Test(String name, String hello)
			
			final Test test = new Test("Vladimir", "Hello, ");
			System.out.println(test.hello + test.name);

- Lazy initialization of variables:
	- Usage:
		
			lazy <Type> <Identifier> = <Identifier>;
		
		or
		
			lazy <Type> <Identifier> = { return <Identifier>; }
	
	- Result in Java:
	
			final Lazy<Type> <Identifier> = new Lazy<Type>(new F1<Type>() {
				@Override
				public <Type> invoke() {
					/* to do something */
					return <Identifier>;
				}
			});
		
	- Example:
	
			lazy String s = "Lazy string";
			System.out.println(s.invoke());
		
			lazy String s1 = { System.out.println("Requesting lazy string..."); return "Lazy string 2"; }
			System.out.println(s1.invoke());

- Closure without result and parameters:
	- Usage:
	
			<Identifier> = () => { /* to do something */ }
		
		or 
			
			<Identifier> = => {  }
	
	- Result in Java:
	
			final F <Identifier> = new F() {
				@Override
				public void invoke() {
					/* to do something */
				}
			};
	
	- Example:
		
			f = () => { System.out.println("Closure without parameters"); }
			f.invoke();
		
			f1 = => { System.out.println("Closure without parameters and less to write"); }
			f1.invoke();
		
	- As parameter:
		
			void someFunction(( =>) f) { /* to do something */ f.invoke(); }

		!WARNING: in Java code () => f and => f are same language constructions, you cannot define two functions above at the same time.
		
			someFunction(() => { /* to do something */ });
		
		or
		
			someFunction( => { /* to do something */ });
		
	- As anonymous:
		
			() => { /* to do something */ }.invoke();
		
		or
			
			=> { /* to do something */ }.invoke();
		
- Closure without parameters but with result:
	- Usage:
		
			<Identifier> = (<Type>) => { return <Identifier>; }
			
		or
		
			<Identifier> = (<Type>) => <Identifier>;
	
	- Result in Java:
		
			final F1<Type> <Identifier> = new F1<Type>() {
				@Override
				public <Type> invoke() {
					/* to do something */
					return <Identifier>;
				}
			};
		
	- Example:
		
			f = (String) => { return "Some closure with result of String"; }
			System.out.println(f.invoke());
			
			f = (String) => "Some single line closure";
			System.out.println(f.invoke());
	
	- As parameter:
		
			void someFunction((String =>) f) { /* to do something */ String s = f.invoke(); }
	
			someFunction((String) => { /* to do something */ return "Some string here"; });
	
	- As anonymous:
		
			(String) => { /* to do something */ return "Some string here"; }.invoke();
			
		or
		
			((String) => "Some simple result").invoke();
			
		or, if you like
		
			(String) => "Some strange result";.invoke();
		
- Closure with arguments and result (now supports only 1 parameter):
	- Usage:
		
			<Identifier> = (<Type>, <Type> <Identifier> [, <Type> <Identifier>]) => { return <Identifier>; }
	
	- Result in Java:
		
			final F2<Type, Type> <Identifier> = new F2<Type, Type>() {
				@Override
				public <Type> invoke(final <Type> <Identifier>) {
					/* to do something */
					return <Identifier>;
				}
			};
	
	- Example:
		
			f = (String, String name) => { return "Hello " + name; }
			System.out.println(f.invoke("Vladimir"));
			
			f = (String, String name) => "Hello " + name;
			System.out.println(f.invoke("Vladimir"));
	
	- As parameter:
		
			void someFunction((String, String =>) f) { /* to do something */ String s = f.invoke("something"); }
		
			someFunction((String, String something) => { /* to do something */ return "We found " + something; });
	
	- As anonymous:
		
			(String, String something) => { /* to do something */ return "We found " + something; }.invoke("Apple");
			
		or
		
			((String, String something) => "We found simple " + something).invoke("Apple");
			
		or, if you like
		
			(String, String something) => "We found strange " + something;.invoke("Apple");
		
