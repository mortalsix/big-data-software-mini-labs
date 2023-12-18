import math 
import os
import time

# load data from file and return two dataset: user_movie and movies
def ReadData():
    file_user_movie = 'ratings.csv'     # userId,movieId,rating,timestamp
    file_movie_info = 'movies.csv'      # movieId,title,genres
    user_moive = {}                     # userId: movieId_list

    with open(file_user_movie) as f:
        is_firsline = True
        for line in f:
            if is_firsline:             # ignore first line 
                is_firsline = False
                continue
            user, item, score = line.split(',')[0:3]
            user_moive.setdefault(user, {})
            user_moive[user][item] = float(score)

    # user_movie Example:
    # user_movie = {
    #     '1' : {
    #         '1' : 4.0,
    #         '3' : 4.0,
    #         '6' : 4.0,
    #         '47' : 5.0,
    #         ... 
    #     },
    #     '3' : {
    #         '31' : 0.5,
    #         '527' : 0.5,
    #         '1587' : 4.5
    #         ... 
    #     }
    #     ...
    # }
    
    movies = {}   # movieId: title
    with open(file_movie_info, encoding='UTF-8') as f:
        is_firsline = True
        for line in f:
            if is_firsline:
                is_firsline = False
                continue
            movieId, movieTitle = line.split(',')[0:2]
            movies[movieId] = movieTitle

    # movies Example:
    # movies = {
    #     '1' : 'Toy Story (1995)',
    #     '2' : 'Jumanji (1995)',
    #     '3' : 'Grumpier Old Men (1995)',
    #     ...
    # }
            
    return user_moive, movies


# caculate item similarity matrix 
def ItemSimilarity(user_movie):
    C = {}
    N = {}
    for user, items in user_movie.items():
        for i in items.keys():
            N.setdefault(i, 0)
            N[i] += 1
            C.setdefault(i, {})
            for j in items.keys():
                if i == j:
                    continue
                C[i].setdefault(j, 0)
                C[i][j] += 1
    W = {}
    for i, related_items in C.items():
        W.setdefault(i, {})
        for j, cij in related_items.items():
            W[i][j] = cij / (math.sqrt(N[i] * N[j]))
    return W

def Recommend(user, user_movie, W, K, N):
    rank = {}
    action_item = user_movie[user]
    for item, score in action_item.items():
        for j, wj in sorted(W[item].items(), key = lambda x : x[1], reverse = True)[0:K]:
            if j in action_item.keys():
                continue
            rank.setdefault(j, 0)
            rank[j] += score * wj
    return dict(sorted(rank.items(), key = lambda x : x[1], reverse = True)[0:N])

if __name__ == "__main__":
    t0 = time.time()
    user_movie, movies = ReadData()
    t1 = time.time()
    print("加载数据用时：", (t1 - t0))
    W = ItemSimilarity(user_movie)
    t2 = time.time()
    print("计算物品相似度矩阵用时：", (t2 - t1))
    result = Recommend('1', user_movie, W, 10, 10)
    t3 = time.time()
    print("推荐用时：", (t3 - t2))
    print("最终推荐结果：")
    for i, rating in result.items():
        print('film: ', movies[i], ', rating: ', rating)
    