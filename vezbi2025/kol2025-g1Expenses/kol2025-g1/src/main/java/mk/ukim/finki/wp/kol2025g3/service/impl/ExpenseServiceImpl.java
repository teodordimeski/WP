package mk.ukim.finki.wp.kol2025g3.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.kol2025g3.model.Expense;
import mk.ukim.finki.wp.kol2025g3.model.ExpenseCategory;
import mk.ukim.finki.wp.kol2025g3.model.Vendor;
import mk.ukim.finki.wp.kol2025g3.model.exceptions.InvalidExpenseIdException;
import mk.ukim.finki.wp.kol2025g3.repository.ExpenseRepository;
import mk.ukim.finki.wp.kol2025g3.service.ExpenseService;
import mk.ukim.finki.wp.kol2025g3.service.VendorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import mk.ukim.finki.wp.kol2025g3.service.impl.FieldFilterSpecification.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    VendorService vendorService;
    ExpenseRepository expenseRepository;

    @Override
    public List<Expense> listAll() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense findById(Long id) {
        return expenseRepository.findById(id).orElseThrow(InvalidExpenseIdException::new);
    }

    @Override
    public Expense create(String title, LocalDate dateCreated, Double amount, Integer daysToExpire, ExpenseCategory expenseCategory, Long vendorId) {
        Vendor vendor = vendorService.findById(vendorId);
        return expenseRepository.save(new Expense(title,dateCreated,amount,daysToExpire,expenseCategory,vendor));
    }

    @Override
    public Expense update(Long id, String title, LocalDate dateCreated, Double amount, Integer daysToExpire, ExpenseCategory expenseCategory, Long vendorId) {
        Vendor vendor = vendorService.findById(vendorId);
        Expense expense = findById(id);

        expense.setTitle(title);
        expense.setDateCreated(dateCreated);
        expense.setAmount(amount);
        expense.setDaysToExpire(daysToExpire);
        expense.setExpenseCategory(expenseCategory);
        expense.setVendor(vendor);

        return expenseRepository.save(expense);
    }

    @Override
    public Expense delete(Long id) {
        Expense expense = findById(id);
        expenseRepository.delete(expense);

        return expense;
    }

    @Override
    public Expense extendExpiration(Long id) {
        Expense expense = findById(id);
        expense.setDaysToExpire(expense.getDaysToExpire() + 1);

        return expenseRepository.save(expense);
    }

    @Override
    public Page<Expense> findPage(String title, ExpenseCategory expenseCategory, Long vendor, int pageNum, int pageSize) {
        Specification<Expense> specification=Specification.allOf(
            FieldFilterSpecification.filterContainsText(Expense.class,"title", title),
                FieldFilterSpecification.filterEqualsV(Expense.class,"expenseCategory", expenseCategory),
                FieldFilterSpecification.filterEqualsV(Expense.class,"vendor.id", vendor)
        );

        return expenseRepository.findAll(specification, PageRequest.of(pageNum, pageSize));
    }
}
