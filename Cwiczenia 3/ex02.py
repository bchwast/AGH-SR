# Excercises 2.1) Create large lists and python dictionaries,
# put them in object store. Write a Ray task to process them.
import cProfile
import logging

import ray

ray.init(address='auto', ignore_reinit_error=True, logging_level=logging.ERROR)


@ray.remote
def process_large_list(large_list):
    processed_list = [x + 1 for x in large_list]
    return processed_list


@ray.remote
def process_large_dict(large_dict):
    processed_dict = {k: v + 1 for k, v in large_dict.items()}
    return processed_dict

@ray.remote
def process_list_and_dict(large_list, large_dict):
    processed_something = [large_list[i] + large_dict[i] for i in range(len(large_list))]
    return processed_something

SIZE = 1000000
quite_large_list = list(range(SIZE))
quite_large_dict = {k: k for k in range(SIZE)}

quite_large_list_ref = ray.put(quite_large_list)
quite_large_dict_ref = ray.put(quite_large_dict)

cProfile.run('ray.get(process_large_list.remote(quite_large_list_ref))')
cProfile.run('ray.get(process_large_dict.remote(quite_large_dict_ref))')
cProfile.run('ray.get(process_list_and_dict.remote(quite_large_list_ref, quite_large_dict_ref))')

ray.shutdown()
