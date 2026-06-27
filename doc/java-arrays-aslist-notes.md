# Java Arrays.asList Notes

This note explains the confusion around `Arrays.asList(...)`, especially for `int[]`, `Integer[]`, and `int[][]`.

## Main Idea

`Arrays.asList(array)` works cleanly when the array is an array of objects.

But primitive arrays like `int[]` behave differently because Java generics do not support primitive types directly.

There is no `List<int>` in Java. You must use `List<Integer>`.

## Case 1: `Integer[]`

```java
Integer[] arr = {1, 2, 3};

List<Integer> list = Arrays.asList(arr);

System.out.println(list); // [1, 2, 3]
```

This works as expected because `Integer` is an object type.

Result:

```text
List<Integer> with 3 elements: 1, 2, 3
```

## Case 2: `int[]`

```java
int[] arr = {1, 2, 3};

List<int[]> list = Arrays.asList(arr);

System.out.println(list.size()); // 1
```

This does not create a list like `[1, 2, 3]`.

Instead, it creates a list with one element: the whole `int[]` array.

Result:

```text
List<int[]> with 1 element: the entire int[] arr
```

Why?

Because `int[]` itself is an object, but the values inside it are primitive `int`s.

So Java treats this:

```java
Arrays.asList(arr)
```

as:

```java
List<int[]>
```

not:

```java
List<Integer>
```

## Correct Way To Convert `int[]` To `List<Integer>`

```java
int[] arr = {1, 2, 3};

List<Integer> list = Arrays.stream(arr)
        .boxed()
        .toList();
```

If you need a mutable list:

```java
import java.util.*;
import java.util.stream.*;

int[] arr = {1, 2, 3};

List<Integer> list = Arrays.stream(arr)
        .boxed()
        .collect(Collectors.toCollection(ArrayList::new));

list.add(4);
```

## Case 3: `int[][]`

```java
int[][] intervals = {
        {1, 3},
        {6, 9}
};

List<int[]> list = Arrays.asList(intervals);

System.out.println(list.size()); // 2
```

This works when you want a list of rows.

Why?

Because `int[][]` is an array of `int[]` rows.

```java
intervals[0] // int[]{1, 3}
intervals[1] // int[]{6, 9}
```

So this:

```java
Arrays.asList(intervals)
```

creates:

```java
List<int[]>
```

with each row as one element.

That is useful for interval problems.

Example:

```java
int[][] intervals = {
        {1, 3},
        {6, 9}
};

List<int[]> list = new ArrayList<>(Arrays.asList(intervals));

list.add(new int[]{10, 12});
list.remove(0);

int[][] result = list.toArray(new int[list.size()][]);
```

## Why Use `new ArrayList<>(Arrays.asList(...))`?

`Arrays.asList(...)` returns a fixed-size list.

This is allowed:

```java
List<int[]> list = Arrays.asList(intervals);

list.set(0, new int[]{2, 5});
```

But this is not allowed:

```java
list.add(new int[]{10, 12});    // UnsupportedOperationException
list.remove(0);                 // UnsupportedOperationException
```

So if you need to add or remove rows, wrap it in a real `ArrayList`:

```java
List<int[]> list = new ArrayList<>(Arrays.asList(intervals));

list.add(new int[]{10, 12});
list.remove(0);
```

## Quick Summary

| Input type | Code | Result |
|---|---|---|
| `Integer[]` | `Arrays.asList(arr)` | `List<Integer>` with each number |
| `int[]` | `Arrays.asList(arr)` | `List<int[]>` with one element: the whole array |
| `int[][]` | `Arrays.asList(arr)` | `List<int[]>` with each row |

## Rule To Remember

If you have this:

```java
int[] arr = {1, 2, 3};
```

and you want this:

```java
List<Integer> list = [1, 2, 3]
```

use:

```java
List<Integer> list = Arrays.stream(arr).boxed().toList();
```

If you have this:

```java
int[][] intervals = {{1, 3}, {6, 9}};
```

and you want a list of interval rows, use:

```java
List<int[]> list = new ArrayList<>(Arrays.asList(intervals));
```
