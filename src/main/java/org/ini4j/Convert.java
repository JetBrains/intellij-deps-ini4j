/*
 * Copyright 2005 [ini4j] Development Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ini4j;

class Convert
{
    protected static String escape(String line)
    {
        int len = line.length();
        StringBuilder buffer = new StringBuilder(len*2);

        for(int i = 0 ; i < len; i++)
        {
            char c = line.charAt(i);
            int idx = "\\\t\n\f".indexOf(c);

            if ( idx >= 0 )
            {
                buffer.append('\\');
                buffer.append( "\\tnf".charAt(idx));
            }
            else
            {
                if ((c < 0x0020) || (c > 0x007e))
                {
                    buffer.append("\\u");
                    buffer.append(Integer.toHexString(c));
                }
                else
                {
                    buffer.append(c);
                }
            }
        }
        return buffer.toString();
    }

    protected static String unescape(String line)
    {
        int n = line.length();
        StringBuilder buffer = new StringBuilder(n);
        
        for(int i = 0; i < n; )
        {
            char c = line.charAt(i++);
            
            if ( c == '\\' )
            {
                c = line.charAt(i++);
                
                if ( c == 'u' )
                {
                    try
                    {
                        c = (char) Integer.parseInt(line.substring(i,i+=4), 16);
                    }
                    catch (RuntimeException x)
                    {
                        throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
                    }
                }
                else
                {
                    int idx = "\\tnf".indexOf(c);
                    
                    if ( idx >= 0 )
                    {
                        c = "\\\t\n\f".charAt(idx);
                    }
                }
            }
            
            buffer.append(c);
        }
        
        return buffer.toString();
    }
}
