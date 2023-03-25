from fastapi import FastAPI
from enum import Enum

app=FastAPI( )

# sample requests and queries

@app.get("/")
async def root() :
    return {"message" : "Hello World"}

@app.get("/hello/{name}")
async def say_hello(name: str) :
    return {"message" : f"Hello {name}"}

# Path paramters predefined values

class ModelName(str, Enum):
    alexnet = "alexnet"
    resnet = "resnet"
    lenet = "lenet"

@app.get("/v1/models/{model_name}")
async def get_model(model_name: ModelName):
    if model_name is ModelName.alexnet:
        return {"model_name": model_name, "message": "Deep Learning FTW!"}

    if model_name.value == "lenet":
        return {"model_name": model_name, "message": "LeCNN all the images"}

    return {"model_name": model_name, "message": "Have some residuals"}

# query paramters

fake_items_db = [{"item_name": "Foo"}, {"item_name": "Bar"}, {"item_name": "Baz"}]

@app.get("/v2/items/")
async def read_item(skip: int = 0, limit: int = 10):
    return fake_items_db[skip : skip + limit]

# Optional parameters
from typing import Union

#In this case, there are 3 query parameters:
# needy, a required str.
# skip, an int with a default value of 0.
# limit, an optional int.

@app.get("/v3/items/{item_id}")
async def read_user_item(
    item_id: str, needy: str, skip: int = 0, limit: Union[int, None] = None
):
    item = {"item_id": item_id, "needy": needy, "skip": skip, "limit": limit}
    return item

# Request Body
from pydantic import BaseModel

class Item(BaseModel):
    name: str
    description: Union[str, None] = None
    price: float
    tax: Union[float, None] = None
# create model
@app.post("/v4/items/")
async def create_item(item: Item):
    return item
# using model

@app.post("/v5/items/")
async def create_item(item: Item):
    item_dict = item.dict()
    if item.tax:
        price_with_tax = item.price + item.tax
        item_dict.update({"price_with_tax": price_with_tax})
    return item_dict

# all together

@app.put("/v6/items/{item_id}")
async def create_item(item_id: int, item: Item, q: Union[str, None] = None):
    result = {"item_id": item_id, **item.dict()}
    if q:
        result.update({"q": q})
    return result

# If the parameter is also declared in the path, it will be used as a path parameter.
# If the parameter is of a singular type (like int, float, str, bool, etc) it will be interpreted as a query parameter.
# If the parameter is declared to be of the type of a Pydantic model, it will be interpreted as a request body.

# additional status code:
# https://fastapi.tiangolo.com/advanced/additional-status-codes/

from fastapi import Body, FastAPI, status
from fastapi.responses import JSONResponse

items = {"foo": {"name": "Fighters", "size": 6}, "bar": {"name": "Tenders", "size": 3}}

@app.put("/v7/items/{item_id}")
async def upsert_item(
    item_id: str,
    name: Union[str, None] = Body(default=None),
    size: Union[int, None] = Body(default=None),
):
    if item_id in items:
        item = items[item_id]
        item["name"] = name
        item["size"] = size
        return item
    else:
        item = {"name": name, "size": size}
        items[item_id] = item
        return JSONResponse(status_code=status.HTTP_201_CREATED, content=item)

from typing import List

class Poll:
    def __init__(self, question, votes):
        self.question = question
        self.votes = votes

polls = {}

@app.get('/poll')
async def get_polls():
    return polls

@app.post('/poll')
async def create_poll(question: str):
    id = -1
    for i in polls.keys():
        if i > id:
            id = i
    id += 1
    poll = Poll(question, [])
    polls[id] = poll
    return {id, poll}

@app.get('/poll/{id}')
async def get_poll_with_id(id: int):
    if len(polls) <= id:
        return JSONResponse(status_code=status.HTTP_404_NOT_FOUND, content=id)
    return polls[id]

@app.put('/pool/{id}')
async def update_pool_with_id(id: int, question: Union[str, None] = None, votes: Union[List, None] = None):
    if len(polls) <= id:
        return JSONResponse(status_code=status.HTTP_404_NOT_FOUND, content=id)
    if question:
        polls[id].question = question
    if votes:
        polls[id].votes = votes
    return polls[id]

@app.delete('/pool/{id}')
async def delete_poll_with_id(id: int):
    if len(polls) <= id:
        return JSONResponse(status_code=status.HTTP_404_NOT_FOUND, content=id)
    return polls.pop(id)

@app.get('/pool/{id}/vote')
async def get_poll_votes(id: int):
    if len(polls) <= id:
        return JSONResponse(status_code=status.HTTP_404_NOT_FOUND, content=id)
    return polls[id].votes

@app.post('/pool/{id}/vote')
async def cast_vote_in_poll(id: int, vote: str):
    if len(polls) <= id:
        return JSONResponse(status_code=status.HTTP_404_NOT_FOUND, content=id)
    polls[id].votes.append(vote)
    return vote

# @app.get('/pool/{id}/vote/{id}')
# async def get_vote_in_poll(poll_id: int, vote_id: int)
