# Rotated Sorted Array Variants

This note summarizes the rotation-point and search variants discussed. It keeps the practice-repo mindset: start from the original idea, then patch the weak points with small, deliberate edits.

## Definitions

### Minimum index

The index of any minimum value in the array.

Example:

```java
[1, 1, 1, 2, 1, 1]
```

Minimum value is `1`, so index `0` is a valid minimum index.

### Rotation point / drop point

The index immediately after the decreasing edge:

```java
nums[i - 1] > nums[i]
```

Example:

```java
[1, 1, 1, 2, 1, 1]
             ^
```

Rotation point is index `4`, because:

```java
nums[3] = 2
nums[4] = 1
nums[3] > nums[4]
```

These two concepts can differ when duplicates exist.

## 1. Original Idea For Distinct Values

For distinct values, the original last element can separate the two parts of the rotated array.

```java
int findRotationPointDistinct(int[] nums) {
    int low = 0;
    int high = nums.length - 1;
    int right = nums[high];

    while (low <= high) {
        int mid = low + (high - low) / 2;

        if ((mid == 0 || nums[mid - 1] > nums[mid]) && nums[mid] <= right) {
            return mid;
        }

        if (nums[mid] > right) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }

    return 0;
}
```

Works for distinct arrays:

```java
[4, 5, 6, 7, 0, 1, 2] -> 4
[3, 4, 5, 1, 2]       -> 3
[1, 2, 3, 4, 5]       -> 0
[5]                   -> 0
```

Why `<= right` is safe here:

```text
Distinct values mean the original right value cleanly separates the two groups.
nums[mid] > right  => left/high-value part
nums[mid] <= right => right/low-value part
```

## 2. Why Fixed `right` Breaks With Duplicates

With duplicates, fixed `right = nums[n - 1]` no longer cleanly separates the array.

Example:

```java
[1, 0, 1, 1, 1]
```

Here:

```java
right = 1
```

But `1` appears before and after the rotation point:

```text
index 0: 1 before pivot
index 2: 1 after pivot
index 3: 1 after pivot
index 4: 1 after pivot
```

So this check no longer proves the side:

```java
nums[mid] <= right
```

## 3. Find Any Minimum Index With Duplicates

If the task is only to find any minimum index, use the moving boundary `nums[high]`.

```java
int findMinimumIndexWithDuplicates(int[] nums) {
    int low = 0;
    int high = nums.length - 1;

    while (low < high) {
        int mid = low + (high - low) / 2;

        if (nums[mid] > nums[high]) {
            low = mid + 1;
        } else if (nums[mid] < nums[high]) {
            high = mid;
        } else {
            high--;
        }
    }

    return low;
}
```

Meaning:

```text
nums[mid] > nums[high]  => minimum is to the right of mid
nums[mid] < nums[high]  => nums[mid..high] is sorted, minimum is at mid or left
nums[mid] == nums[high] => duplicate ambiguity, shrink high
```

Works for finding a minimum index:

```java
[1, 0, 1, 1, 1]       -> 1
[2, 2, 2, 0, 1, 2]    -> 3
[1, 1, 1, 1]          -> 0
[11, 12, 13, 14]      -> 0
```

Important: this finds a minimum index, not necessarily the exact drop point.

Worst-case complexity with duplicates is `O(n)`.

## 4. The `ans = mid; high = mid - 1` Trap

This was the important bug found in practice.

Problem pattern:

```java
} else if (nums[mid] < nums[high]) {
    ans = mid;
    high = mid - 1;
}
```

This is dangerous if `ans` is later returned as the exact rotation point.

Counterexample:

```java
[11, 12, 13, 14]
```

Dry run:

```text
low=0 high=3 mid=1
nums[mid]=12 nums[high]=14
nums[mid] < nums[high]
ans=1
high=0

low=0 high=0 mid=0
nums[mid] == nums[high]
high--

loop ends
return ans=1
```

Expected answer:

```java
0
```

Lesson:

```text
ans = mid means mid is a candidate minimum, not a proven rotation/drop point.
For exact rotation point, only nums[i - 1] > nums[i] proves the answer.
```

## 5. Exact Rotation Point With Duplicates, Keeping The Same Style

This keeps the same `low <= high`, `mid`, and `high--` style, but removes the unsafe `ans = mid` result.

```java
int findStart(int[] nums) {
    int low = 0;
    int high = nums.length - 1;

    while (low <= high) {
        int mid = low + (high - low) / 2;

        // Only a real drop proves the rotation point.
        if (mid > 0 && nums[mid - 1] > nums[mid]) {
            return mid;
        }

        // Also check the edge immediately after mid.
        if (mid + 1 < nums.length && nums[mid] > nums[mid + 1]) {
            return mid + 1;
        }

        if (nums[mid] > nums[high]) {
            low = mid + 1;
        } else if (nums[mid] < nums[high]) {
            high = mid - 1;
        } else {
            // Before discarding high, check whether high itself is the drop point.
            if (high > 0 && nums[high - 1] > nums[high]) {
                return high;
            }
            high--;
        }
    }

    // No drop found means not rotated.
    return 0;
}
```

Checks:

```java
[11, 12, 13, 14] -> 0
[4, 5, 6, 7, 0, 1, 2] -> 4
[1, 0, 1, 1, 1] -> 1
[2, 2, 2, 0, 1, 2] -> 3
[1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1] -> 14
[1, 1, 1, 1] -> 0
```

Why `mid == 0` was removed:

```text
mid == 0 only means the search is currently looking at index 0.
It does not prove no rotation exists.
Return 0 only after no drop was found anywhere.
```

Worst-case complexity with duplicates is still `O(n)`.

## 6. Guaranteed Simple Exact Rotation Point

The simplest guaranteed-correct exact drop-point solution is linear:

```java
int findExactRotationPointWithDuplicates(int[] nums) {
    for (int i = 1; i < nums.length; i++) {
        if (nums[i - 1] > nums[i]) {
            return i;
        }
    }

    return 0;
}
```

This directly matches the definition:

```java
nums[i - 1] > nums[i]
```

## 7. Search Target In Rotated Sorted Array With Duplicates

If the question is to find whether a target exists, do not find the rotation point first. Search directly.

This version uses the `high` side first:

```java
boolean search(int[] nums, int target) {
    int low = 0;
    int high = nums.length - 1;

    while (low <= high) {
        int mid = low + (high - low) / 2;

        if (nums[mid] == target) {
            return true;
        }

        if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
            low++;
            high--;
        } else if (nums[mid] <= nums[high]) {
            // Right half [mid..high] is sorted.
            if (nums[mid] < target && target <= nums[high]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        } else {
            // Left half [low..mid] is sorted.
            if (nums[low] <= target && target < nums[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
    }

    return false;
}
```

Examples:

```java
[2, 5, 6, 0, 0, 1, 2], target = 0 -> true
[2, 5, 6, 0, 0, 1, 2], target = 3 -> false
```

Worst-case complexity with duplicates is `O(n)`.

## Summary

```text
Distinct values + rotation point:
Fixed right works. O(log n).

Duplicates + any minimum index:
Use nums[high] and high--. Worst O(n).

Duplicates + exact drop/rotation point:
Only return when nums[i - 1] > nums[i]. Hybrid is possible, worst O(n).

ans = mid:
Candidate minimum, not proof of exact rotation point.

Search target with duplicates:
Use modified binary search directly. Worst O(n).
```
