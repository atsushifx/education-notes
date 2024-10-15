# hindo_max.rb
# input: message
# output: max count of alphabet histgram


def hindo(message)
  code_a = 97
  leng = message.length
  a = message.unpack("C*")
  h = Array.new(26, 0)

  for i in 0..(leng-1)
    sa = a[ i ] - code_a
    if (0<=sa && sa<=26-1) then
      h[sa] = h[sa] + 1
    end
  end
  return h
end

def hindo_max(h)
  max = -1
  max_sa = -1
  for sa in 0..25
    if h[sa] > max
      max = h[sa]
      max_sa = sa
    end
  end
  return max_sa, max
end

angobun = gets.chomp
h = hindo(angobun)
max_sa, max = hindo_max(h)
puts(max, max_sa)


