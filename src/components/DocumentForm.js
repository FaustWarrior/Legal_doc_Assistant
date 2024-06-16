import React, { useState } from 'react';
import axios from 'axios';

const DocumentForm = () => {
  const [documentType, setDocumentType] = useState('');
  const [partyOne, setPartyOne] = useState('');
  const [partyTwo, setPartyTwo] = useState('');
  const [agreementTerms, setAgreementTerms] = useState('');
  const [error, setError] = useState('');
  const [documents, setDocuments] = useState([]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const token = localStorage.getItem('jwt');
    if (!token) {
      setError('No token found. Please log in.');
      return;
    }

    try {
      const response = await axios.post(
        'http://localhost:8080/api/documents',
        {
          documentType,
          partyOne,
          partyTwo,
          agreementTerms
        },
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );
      console.log('Document saved:', response.data);
      setError('');
    } catch (err) {
      setError('Failed to save document. Please try again.');
    }
  };

  const handleGetDocuments = async () => {
    const token = localStorage.getItem('jwt');
    if (!token) {
      setError('No token found. Please log in.');
      return;
    }

    try {
      const response = await axios.get('http://localhost:8080/api/documents', {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
      setDocuments(response.data);
      setError('');
    } catch (err) {
      setError('Failed to fetch documents. Please try again.');
    }
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Document Type"
          value={documentType}
          onChange={(e) => setDocumentType(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="Party One"
          value={partyOne}
          onChange={(e) => setPartyOne(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="Party Two"
          value={partyTwo}
          onChange={(e) => setPartyTwo(e.target.value)}
          required
        />
        <textarea
          placeholder="Agreement Terms"
          value={agreementTerms}
          onChange={(e) => setAgreementTerms(e.target.value)}
          required
        ></textarea>
        <button type="submit">Submit Document</button>
      </form>
      {error && <p>{error}</p>}
      <button onClick={handleGetDocuments}>Get Documents</button>
      {documents.length > 0 && (
        <ul>
          {documents.map(doc => (
            <li key={doc.id}>{doc.documentType} - {doc.partyOne} and {doc.partyTwo}</li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default DocumentForm;
