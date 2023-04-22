# 3.3 Take a look on implement parralel Pi computation
# based on https://docs.ray.io/en/master/ray-core/examples/highly_parallel.html
#
# Implement calculating pi as a combination of actor (which keeps the
# state of the progress of calculating pi as it approaches its final value)
# and a task (which computes candidates for pi)
import cProfile
import logging

import ray
import random
import math

ray.init(address='auto', ignore_reinit_error=True, logging_level=logging.ERROR)


@ray.remote
def pi4_sample(sample_count):
    """pi4_sample runs sample_count experiments, and returns the
    fraction of time it was inside the circle.
    """
    in_count = 0
    for i in range(sample_count):
        x = random.random()
        y = random.random()
        if x * x + y * y <= 1:
            in_count += 1

    return in_count, sample_count


@ray.remote
class PiCalculator:
    def __init__(self):
        self.in_count = 0
        self.sample_count = 0

    def add_sample(self, in_count, sample_count):
        self.in_count += in_count
        self.sample_count += sample_count

    def get_pi(self):
        if self.sample_count == 0:
            return 0
        return 4 * (self.in_count / self.sample_count)

    def get_sample_count(self):
        return self.sample_count


def approx_pi(sample_count, task_count):
    pi_calculator = PiCalculator.remote()

    tasks = [pi4_sample.remote(sample_count) for _ in range(task_count)]

    for task in tasks:
        in_count, sample_count = ray.get(task)
        pi_calculator.add_sample.remote(in_count, sample_count)

    final_result = float(ray.get(pi_calculator.get_pi.remote()))
    samples = ray.get(pi_calculator.get_sample_count.remote())
    print(f'Final result: {final_result}, error: {abs(final_result - math.pi)}')
    print(f'Number of samples: {samples}')


SAMPLE_COUNT = 1000000
TASK_COUNT = 1000

cProfile.run('approx_pi(SAMPLE_COUNT, TASK_COUNT)')

ray.shutdown()
