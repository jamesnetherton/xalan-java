/*
 * The Apache Software License, Version 1.1
 *
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights 
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer. 
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:  
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Xalan" and "Apache Software Foundation" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written 
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation and was
 * originally based on software copyright (c) 1999, Lotus
 * Development Corporation., http://www.lotus.com.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */
package org.apache.xalan.utils;

/**
 * <meta name="usage" content="internal"/>
 * This class holds information about the namespace info
 * of a node.  It is used to optimize namespace lookup in
 * a generic DOM.
 */
public class NSInfo
{

  /**
   * Constructor NSInfo
   *
   *
   * NEEDSDOC @param hasProcessedNS
   * NEEDSDOC @param hasXMLNSAttrs
   */
  public NSInfo(boolean hasProcessedNS, boolean hasXMLNSAttrs)
  {

    m_hasProcessedNS = hasProcessedNS;
    m_hasXMLNSAttrs = hasXMLNSAttrs;
    m_namespace = null;
    m_ancestorHasXMLNSAttrs = ANCESTORXMLNSUNPROCESSED;
  }

  // Unused at the moment

  /**
   * Constructor NSInfo
   *
   *
   * NEEDSDOC @param hasProcessedNS
   * NEEDSDOC @param hasXMLNSAttrs
   * NEEDSDOC @param ancestorHasXMLNSAttrs
   */
  public NSInfo(boolean hasProcessedNS, boolean hasXMLNSAttrs,
                int ancestorHasXMLNSAttrs)
  {

    m_hasProcessedNS = hasProcessedNS;
    m_hasXMLNSAttrs = hasXMLNSAttrs;
    m_ancestorHasXMLNSAttrs = ancestorHasXMLNSAttrs;
    m_namespace = null;
  }

  /**
   * Constructor NSInfo
   *
   *
   * NEEDSDOC @param namespace
   * NEEDSDOC @param hasXMLNSAttrs
   */
  public NSInfo(String namespace, boolean hasXMLNSAttrs)
  {

    m_hasProcessedNS = true;
    m_hasXMLNSAttrs = hasXMLNSAttrs;
    m_namespace = namespace;
    m_ancestorHasXMLNSAttrs = ANCESTORXMLNSUNPROCESSED;
  }

  /** NEEDSDOC Field m_namespace          */
  public String m_namespace;

  /** NEEDSDOC Field m_hasXMLNSAttrs          */
  public boolean m_hasXMLNSAttrs;

  /** NEEDSDOC Field m_hasProcessedNS          */
  public boolean m_hasProcessedNS;

  /** NEEDSDOC Field m_ancestorHasXMLNSAttrs          */
  public int m_ancestorHasXMLNSAttrs;

  /** NEEDSDOC Field ANCESTORXMLNSUNPROCESSED          */
  public static final int ANCESTORXMLNSUNPROCESSED = 0;

  /** NEEDSDOC Field ANCESTORHASXMLNS          */
  public static final int ANCESTORHASXMLNS = 1;

  /** NEEDSDOC Field ANCESTORNOXMLNS          */
  public static final int ANCESTORNOXMLNS = 2;
}
