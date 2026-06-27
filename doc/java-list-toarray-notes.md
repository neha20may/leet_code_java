# Java List.toArray Notes

This note explains the input passed to `list.toArray(...)`.

## What `toArray()` Does

`toArray()` converts a `List` into an array.

There are two common forms:

```java
Object[] arr = list.toArray();
```

and:

```java
String[] arr = list.toArray(new String[0]);
```

The second form is usually better because it returns the exact array type you want.

## Why Not Just `list.toArray()`?

```java
List<String> list = Arrays.asList("a", "b", "c");

Object[] arr = list.toArray();
```

This returns:

```java
Object[]
```

not:

```java
String[]
```

So this does not compile:

```java
String[] arr = list.toArray(); // compile error
```

To get a `String[]`, pass a sample `String[]`:

```java
String[] arr = list.toArray(new String[0]);
```

## What Does `new String[0]` Mean?

```java
new String[0]
```

means:

```text
create a String array with 0 slots
```

But in `toArray(...)`, it mainly tells Java:

```text
return a String[]
```

The `0` does not mean the final result has size 0.

Example:

```java
List<String> list = Arrays.asList("a", "b", "c");

String[] arr = list.toArray(new String[0]);

System.out.println(Arrays.toString(arr));
```

Output:

```text
[a, b, c]
```

Java sees that the given array is too small, so it creates a new array with the correct size.

## Can We Use `list.size()` Instead?

Yes.

```java
String[] arr = list.toArray(new String[list.size()]);
```

This gives Java an array with the exact size already.

Both are valid:

```java
String[] a = list.toArray(new String[0]);
String[] b = list.toArray(new String[list.size()]);
```

For practice, remember this version:

```java
String[] arr = list.toArray(new String[0]);
```

## Can We Write `new String[]` Without A Size?

No.

This is invalid Java:

```java
String[] arr = list.toArray(new String[]); // compile error
```

When using `new` to create an array, Java needs a size:

```java
new String[0]
new String[3]
new String[list.size()]
```

## Can We Pass A Random Big Size?

Java allows it:

```java
String[] arr = list.toArray(new String[10000]);
```

But do not do this in normal code.

If the array is bigger than the list, Java fills the real values first and then leaves extra slots as `null`.

Example:

```java
List<String> list = Arrays.asList("a", "b", "c");

String[] arr = list.toArray(new String[5]);

System.out.println(Arrays.toString(arr));
```

Output:

```text
[a, b, c, null, null]
```

So this is allowed, but wasteful and confusing:

```java
String[] arr = list.toArray(new String[10000]);
```

## Interval Problem Example: `List<int[]>` To `int[][]`

For interval problems, you often have:

```java
List<int[]> list = new ArrayList<>();

list.add(new int[]{1, 3});
list.add(new int[]{6, 9});
```

The list type is:

```java
List<int[]>
```

Each element is an `int[]` row.

So the array version is:

```java
int[][]
```

Use:

```java
int[][] arr = list.toArray(new int[0][]);
```

This part:

```java
new int[0][]
```

means:

```text
return an int[][] array
```

The first `[0]` is the number of outer row slots in the sample array.

The second `[]` is empty because each row is already an `int[]`, and rows may have different lengths.

You may also see:

```java
int[][] arr = list.toArray(new int[list.size()][]);
```

Both are valid.

## Pattern To Remember

```java
List<String>  -> String[]  -> list.toArray(new String[0])
List<Integer> -> Integer[] -> list.toArray(new Integer[0])
List<int[]>   -> int[][]   -> list.toArray(new int[0][])
```

## Simple Rule

Use `0` unless you have a reason not to:

```java
String[] arr = list.toArray(new String[0]);
int[][] intervals = list.toArray(new int[0][]);
```
