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
package org.apache.xpath;

import java.io.IOException;

import java.util.StringTokenizer;

import org.apache.xpath.res.XPATHErrorResources;
import org.apache.xalan.res.XSLMessages;
import org.apache.xpath.DOMHelper;

import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.w3c.dom.Attr;
import org.xml.sax.InputSource;
import org.xml.sax.Parser;

import javax.xml.parsers.*;

import javax.xml.transform.TransformerException;

/**
 * <meta name="usage" content="general"/>
 * Provides XSLTProcessor an interface to the Xerces XML parser.  This
 * liaison should be used if Xerces DOM nodes are being processed as
 * the source tree or as the result tree.
 * @see org.apache.xalan.xslt.XSLTProcessor
 * @see org.apache.xml.parsers
 */
public class DOM2Helper extends DOMHelper
{

  /**
   * Construct an instance.
   */
  public DOM2Helper(){}

  /**
   * <meta name="usage" content="internal"/>
   * Check node to see if it was created by a DOM implementation
   * that this helper is intended to support. This is currently
   * disabled, and assumes all nodes are acceptable rather than checking
   * that they implement org.apache.xerces.dom.NodeImpl.
   *
   * @param node The node to be tested.
   *
   * @throws TransformerException if the node is not one which this
   * DOM2Helper can support. If we return without throwing the exception,
   * the node is compatable.
   */
  public void checkNode(Node node) throws TransformerException
  {

    // if(!(node instanceof org.apache.xerces.dom.NodeImpl))
    //  throw new TransformerException(XSLMessages.createXPATHMessage(XPATHErrorResources.ER_XERCES_CANNOT_HANDLE_NODES, new Object[]{((Object)node).getClass()})); //"DOM2Helper can not handle nodes of type"
    //+((Object)node).getClass());
  }

  /**
   * Returns true if the DOM implementation handled by this helper
   * supports the SAX ContentHandler interface.
   *
   * @return true (since Xerces does).
   */
  public boolean supportsSAX()
  {
    return true;
  }

  /** Field m_doc: Document Node for the document this helper is currently
   * accessing or building
   * @see setDocument
   * @see getDocument
   *  */
  private Document m_doc;

  /**
   * Specify which document this helper is currently operating on.
   * 	
   * @param doc The DOM Document node for this document.
   * @see getDocument
   */
  public void setDocument(Document doc)
  {
    m_doc = doc;
  }

  /**
   * Query which document this helper is currently operating on.
   * 	
   * @return The DOM Document node for this document.
   * @see setDocument
   */
  public Document getDocument()
  {
    return m_doc;
  }

  /**
   * <meta name="usage" content="internal"/>
   * Parse an XML document.
   *
   * <p>Right now the Xerces DOMParser class is used.  This needs
   * fixing, either via jaxp, or via some other, standard method.</p>
   *
   * <p>The application can use this method to instruct the SAX parser
   * to begin parsing an XML document from any valid input
   * source (a character stream, a byte stream, or a URI).</p>
   *
   * <p>Applications may not invoke this method while a parse is in
   * progress (they should create a new Parser instead for each
   * additional XML document).  Once a parse is complete, an
   * application may reuse the same Parser object, possibly with a
   * different input source.</p>
   *
   * @param source The input source for the top-level of the
   *        XML document.
   * @exception javax.xml.transform.TransformerException Any SAX exception, possibly
   *            wrapping another exception.
   * @exception java.io.IOException An IO exception from the parser,
   *            possibly from a byte stream or character stream
   *            supplied by the application.
   * @see org.xml.sax.InputSource
   * @see org.xml.sax.XMLReader#setContentHandler
   * @see org.xml.sax.XMLReader#setErrorHandler
   *
   * @throws TransformerException
   */
  public void parse(InputSource source) throws TransformerException
  {

    try
    {

      // I guess I should use JAXP factory here... when it's legal.
      // org.apache.xerces.parsers.DOMParser parser 
      //  = new org.apache.xerces.parsers.DOMParser();
      DocumentBuilderFactory builderFactory =
        DocumentBuilderFactory.newInstance();

      builderFactory.setNamespaceAware(true);
      builderFactory.setValidating(true);

      DocumentBuilder parser = builderFactory.newDocumentBuilder();

      /*
      // domParser.setFeature("http://apache.org/xml/features/dom/create-entity-ref-nodes", getShouldExpandEntityRefs()? false : true);
      if(m_useDOM2getNamespaceURI)
      {
      parser.setFeature("http://apache.org/xml/features/dom/defer-node-expansion", true);
      parser.setFeature("http://xml.org/sax/features/namespaces", true);
      }
      else
      {
      parser.setFeature("http://apache.org/xml/features/dom/defer-node-expansion", false);
      }

      parser.setFeature("http://apache.org/xml/features/allow-java-encodings", true);
      */

      parser.setErrorHandler(
        new org.apache.xml.utils.DefaultErrorHandler());

      // if(null != m_entityResolver)
      // {
      // System.out.println("Setting the entity resolver.");
      //  parser.setEntityResolver(m_entityResolver);
      // }
      setDocument(parser.parse(source));
    }
    catch (org.xml.sax.SAXException se)
    {
      throw new TransformerException(se);
    }
    catch (ParserConfigurationException pce)
    {
      throw new TransformerException(pce);
    }
    catch (IOException ioe)
    {
      throw new TransformerException(ioe);
    }

    // setDocument(((org.apache.xerces.parsers.DOMParser)parser).getDocument());
  }

  /**
   * Given an XML ID, return the element. This requires assistance from the
   * DOM and parser, and is meaningful only in the context of a DTD 
   * or schema which declares attributes as being of type ID. This
   * information may or may not be available in all parsers, may or
   * may not be available for specific documents, and may or may not
   * be available when validation is not turned on.
   *
   * @param id The ID to search for, as a String.
   * @param doc The document to search within, as a DOM Document node.
   * @return DOM Element node with an attribute of type ID whose value
   * uniquely matches the requested id string, or null if there isn't
   * such an element or if the DOM can't answer the question for other
   * reasons.
   */
  public Element getElementByID(String id, Document doc)
  {
    return doc.getElementById(id);
  }

  /**
   * Figure out whether node2 should be considered as being later
   * in the document than node1, in Document Order as defined
   * by the XPath model. This may not agree with the ordering defined
   * by other XML applications.
   * <p>
   * There are some cases where ordering isn't defined, and neither are
   * the results of this function -- though we'll generally return true.
   * <p>
   * TODO: Make sure this does the right thing with attribute nodes!!!
   *
   * @param node1 DOM Node to perform position comparison on.
   * @param node2 DOM Node to perform position comparison on .
   * 
   * @return false if node2 comes before node1, otherwise return true.
   * You can think of this as 
   * <code>(node1.documentOrderPosition &lt;= node2.documentOrderPosition)</code>.
   */
  public boolean isNodeAfter(Node node1, Node node2)
  {

    // Assume first that the nodes are DTM nodes, since discovering node 
    // order is massivly faster for the DTM.
    try
    {
      int index1 = ((DOMOrder) node1).getUid();
      int index2 = ((DOMOrder) node2).getUid();

      return index1 <= index2;
    }
    catch (ClassCastException cce)
    {

      // isNodeAfter will return true if node is after countedNode 
      // in document order. The base isNodeAfter is sloooow (relatively)
      return super.isNodeAfter(node1, node2);
    }
  }

  /**
   * Get the XPath-model parent of a node.  This version takes advantage
   * of the DOM Level 2 Attr.ownerElement() method; the base version we
   * would otherwise inherit is prepared to fall back on exhaustively
   * walking the document to find an Attr's parent.
   *
   * @param node Node to be examined
   *
   * @return the DOM parent of the input node, if there is one, or the
   * ownerElement if the input node is an Attr, or null if the node is
   * a Document, a DocumentFragment, or an orphan.
   */
  public Node getParentOfNode(Node node)
  {
	  Node parent=node.getParentNode();
	  if(parent==null && (Node.ATTRIBUTE_NODE == node.getNodeType()) )
           parent=((Attr) node).getOwnerElement();
	  return parent;
  }

  /**
   * Returns the local name of the given node, as defined by the
   * XML Namespaces specification. This is prepared to handle documents
   * built using DOM Level 1 methods by falling back upon explicitly
   * parsing the node name.
   *
   * @param n Node to be examined
   *
   * @return String containing the local name, or null if the node
   * was not assigned a Namespace.
   */
  public String getLocalNameOfNode(Node n)
  {

    String name = n.getLocalName();

    return (null == name) ? super.getLocalNameOfNode(n) : name;
  }

  /**
   * Returns the Namespace Name (Namespace URI) for the given node.
   * In a Level 2 DOM, you can ask the node itself. Note, however, that
   * doing so conflicts with our decision in getLocalNameOfNode not
   * to trust the that the DOM was indeed created using the Level 2
   * methods. If Level 1 methods were used, these two functions will
   * disagree with each other.
   * <p>
   * TODO: Reconcile with getLocalNameOfNode.
   *
   * @param n Node to be examined
   *
   * @return String containing the Namespace URI bound to this DOM node
   * at the time the Node was created.
   */
  public String getNamespaceOfNode(Node n)
  {
    return n.getNamespaceURI();
  }

  /** Field m_useDOM2getNamespaceURI is a compile-time flag which
   *  gates some of the parser options used to build a DOM -- but 
   * that code is commented out at this time and nobody else
   * references it, so I've commented this out as well. */
  //private boolean m_useDOM2getNamespaceURI = false;
}
