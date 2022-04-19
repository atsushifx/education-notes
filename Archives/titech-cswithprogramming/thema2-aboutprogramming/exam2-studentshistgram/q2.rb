## q2.rb: student hreight histgram
#
# input: stdin > height (100cm ... 199cm)
# output: h[] histgram number
h = Array.new((199-100+1)/10 , 0)

# read student data
data=gets().split.map(&:to_i)
n = data.length
puts(n)  ## student's number


for i in 0..(n-1)
	k = (data[i] - 100) / 10
	h[k] = h[k] + 1
end

puts("Histgram")
puts(h)
