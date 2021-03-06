/**
 * This file is part of Aion-Lightning <aion-lightning.org>.
 *
 *  Aion-Lightning is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Aion-Lightning is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details. *
 *  You should have received a copy of the GNU General Public License
 *  along with Aion-Lightning.
 *  If not, see <http://www.gnu.org/licenses/>.
 */


package com.aionemu.packetsamurai.parser.datatree;

import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;


import com.aionemu.packetsamurai.parser.DataStructure.DataPacketMode;
import com.aionemu.packetsamurai.parser.formattree.Part;


/**
 * 
 * @author Gilles Duboscq
 *
 */
public class FloatValuePart extends ValuePart
{
    private float _float;

    public FloatValuePart(DataTreeNodeContainer parent, Part part)
    {
        super(parent, part);
    }
    
    @Override
    public void parse(ByteBuffer buf)
    {
        if(this.getMode() == DataPacketMode.FORGING)
            throw new IllegalStateException("Can not parse on a Forging mode Data Packet Tree element");
        _float = buf.getFloat();
        // sets the raw bytes
        _bytes = new byte[4];
        buf.position(buf.position()-4);
        buf.get(_bytes);
    }
    
    @Override
    public void forge(DataOutput stream) throws IOException
    {
        if(this.getMode() == DataPacketMode.PARSING)
            throw new IllegalStateException("Can not call forge on a Parsing mode Data Packet Tree element");
        stream.writeDouble(_float);
    }
    
    public void setFloatValue(float d)
    {
        if(this.getMode() == DataPacketMode.PARSING)
            throw new IllegalStateException("Can not set value on a Parsing mode Data Packet Tree element");
        _float = d;
    }
    
    public float getFloatValue()
    {
        return _float;
    }
    
    @Override
    public String getValueAsString()
    {
        return String.valueOf(_float);
    }
}