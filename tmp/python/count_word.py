"""Count words."""

def count_words(s, n):
    """Return the n most frequently occuring words in s."""
    # TODO: Count the number of occurences of each word in s
    dict={} 
    for word in s.split():
        word = word.lower()
        if word in dict:
            dict[word] += 1
        else:
            dict[word] = 1
    
    # TODO: Sort the occurences in descending order (alphabetically in case of ties)
    dict1 = sorted(dict.items(),key=lambda(v, k): (-k, v))
    
    # TODO: Return the top n words as a list of tuples (<word>, <count>)
    top_n = dict1[:n]
    return top_n


def test_run():
    """Test count_words() with some inputs."""
    print count_words("cat bat mat cat bat cat", 3)
    print count_words("betty bought a bit of butter but the butter was bitter", 3)


if __name__ == '__main__':
    test_run()

