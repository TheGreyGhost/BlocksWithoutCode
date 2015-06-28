The mod will automatically register blocks for you and put them on the Rad Pack tab.

It is built as a zip file, all you need to do is edit/add the following files directly in the zip file
* the en_US.lang file
* the blockstates jsons
* the model jsons for block and item
* the textures.
No need to rebuild source code.

Like this:
a) change the "config.blocksolids=2" line in en_US.lang to equal the number of solid blocks you have.
b) for each block, add a blockstates file with filename blockstates/blocksolid1.json, blocksolid2.json, etc
c) for each block, add a block model file (eg models/blocks/blocksolid2model.json)
d) for each block, add a item model file (eg models/item/blocksolid1.json)
e) for each block, add its name to the en_US.lang file eg

As well as solid blocks, you can do the same for "CUTOUT" blocks and for "TRANSLUCENT" blocks, which have a different appearance

see here http://greyminecraftcoder.blogspot.com.au/2014/12/block-rendering-18.html
