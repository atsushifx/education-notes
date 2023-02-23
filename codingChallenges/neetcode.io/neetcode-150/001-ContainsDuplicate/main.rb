# @param {Integer[]} nums
# @return {Boolean}
def contains_duplicate(nums)
  nCnt = Hash.new
  nums.each { |n|
    if nCnt.has_key?(n) then
      nCnt[n] += 1
    else
      nCnt[n] = 1
    end
  }
  mi = nCnt.max_by { |k,v| v }[0]
  return (nCnt[mi] >= 2)
end


p contains_duplicate([1, 2, 3, 1])
