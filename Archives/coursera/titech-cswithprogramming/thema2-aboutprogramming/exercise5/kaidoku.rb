# kaidoku.rb
# input: crypted messagej
# output: message

def dec(k, m)
  code_a = 97
  leng = m.length
  b = Array.new(leng, 0)
  a = m.unpack("C*")

  for  i in 0..(leng-1)
   sa = a[i] - code_a
    if (0<=sa && sa<=26-1) then
      sa = (sa - k + 26) % 26
    end
    b[i] = sa + code_a
  end

  c = b.pack("C*") 
  return c
end

def hindo(message)
  code_a = 97
  leng = message.length
  a = message.unpack("C*")
  h = Array.new(26, 0)

  for i in 0..(leng-1)
    sa = a[i] - code_a
    if (0<=sa && sa<=26-1) then
      h[sa] = h[sa] + 1
    end
  end
  return h
end

def max(h)
  max = -1
  max_sa = -1
  for sa in 0..25
    if h[sa] > max
      max = h[sa]
      max_sa = sa
    end
  end
  return max_sa
end

angobun = gets.chomp
max_sa = max(hindo(angobun))
k = max_sa - 4 # 'e''s index
hirabun = dec(k, angobun)
puts k
puts(hirabun)
