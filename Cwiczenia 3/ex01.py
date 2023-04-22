# Excercises 1.1)Try using local bubble sort and remote bubble sort,
# show difference
import cProfile
import logging
import os

import numpy as np
import ray

ray.init(address='auto', ignore_reinit_error=True, logging_level=logging.ERROR)


def bubble_sort(arr):
    n = len(arr)
    for i in range(n):
        for j in range(0, n - i - 1):
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
    return arr


@ray.remote
def bubble_sort_distributed(arr):
    arr = np.array(arr, copy=True)
    return bubble_sort(arr)


def run_local_sort(sequence_size):
    results = [bubble_sort(np.random.randint(0, 10000, sequence_size)) for _ in range(os.cpu_count())]
    return results


def run_remote_sort(sequence_size):
    results = ray.get(
        [bubble_sort_distributed.remote(np.random.randint(0, 10000, sequence_size)) for _ in range(os.cpu_count())])
    return results


sequence_size = 10000

print('local_sort')
cProfile.run('run_local_sort(sequence_size)')

print('remote_sort')
cProfile.run('run_remote_sort(sequence_size)')

ray.shutdown()
