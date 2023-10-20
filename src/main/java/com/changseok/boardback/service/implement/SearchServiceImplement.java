package com.changseok.boardback.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.changseok.boardback.dto.response.ResponseDto;
import com.changseok.boardback.dto.response.search.GetPopularListResponseDto;
import com.changseok.boardback.dto.response.search.GetRelationListResponseDto;
import com.changseok.boardback.repository.SearchLogRepository;
import com.changseok.boardback.repository.resultSet.SearchWordResultSet;
import com.changseok.boardback.service.SearchService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchServiceImplement implements SearchService {

    private final SearchLogRepository searchLogRepository;
    
    @Override
    public ResponseEntity<? super GetPopularListResponseDto> getPopularList() {

        List<SearchWordResultSet> resultSets = new ArrayList<>();
        
        try {

            resultSets = searchLogRepository.getPopularWordList();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetPopularListResponseDto.success(resultSets);

    }

    @Override
    public ResponseEntity<? super GetRelationListResponseDto> getRelationList(String searchWord) {

        List<SearchWordResultSet> resultSets = new ArrayList<>();

        try {

            resultSets = searchLogRepository.getRelationWordList(searchWord);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetRelationListResponseDto.success(resultSets);
    }

}
