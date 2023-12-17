import math 
import os

def ReadData():
    file_user_movie = 'ratings.csv'
    file_movie_info = 'movies.csv'
    user_moive = {}
    with open(file_user_movie) as f:
        is_firsline = True
        for line in f:
            if is_firsline:
                is_firsline = False
                continue
            user, item, score = line.split(',')[0:3]
            user_moive.setdefault(user, {})
            user_moive[user][item] = float(score)
    movies = {}
    with open(file_movie_info, encoding='UTF-8') as f:
        is_firsline = True
        for line in f:
            if is_firsline:
                is_firsline = False
                continue
            movieId, movieTitle = line.split(',')[0:2]
            movies[movieId] = movieTitle
    return user_moive, movies

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
    user_movie, movies = ReadData()
    W = ItemSimilarity(user_movie)
    result = Recommend('1', user_movie, W, 10, 10)
    for i, rating in result.items():
        print('film: ', movies[i], ', rating: ', rating)
    